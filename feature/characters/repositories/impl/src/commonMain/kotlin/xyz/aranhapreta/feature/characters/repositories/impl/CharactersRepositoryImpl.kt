package xyz.aranhapreta.feature.characters.repositories.impl

import co.touchlab.kermit.Logger
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import xyz.aranhapreta.api.models.`in`.Pagination
import xyz.aranhapreta.database.CharactersDao
import xyz.aranhapreta.database.models.CharacterDbModel
import xyz.aranhapreta.database.models.CharacterPaginationDbModel
import xyz.aranhapreta.feature.books.api.contract.CharactersApi
import xyz.aranhapreta.feature.characters.entities.Character
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState.Failed
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState.Idle
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState.Loading
import xyz.aranhapreta.feature.characters.entities.Gender
import xyz.aranhapreta.feature.characters.entities.Status
import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository
import kotlin.coroutines.coroutineContext
import xyz.aranhapreta.api.models.`in`.Character as CharacterApiModel

internal class CharactersRepositoryImpl(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
) : CharactersRepository {

    private val _state = MutableStateFlow<CharacterLoadingState>(Idle)

    override fun observeAllCharacters(): Flow<List<Character>> {
        return charactersDao
            .observeAllCharacters()
            .map { list -> list.toDomainModel() }
            .onStart {
                val count = charactersDao.countAll()
                if (count == 0) loadPage(1)
            }
    }

    override fun observeState(): Flow<CharacterLoadingState> = _state.asStateFlow()

    private suspend fun loadPage(page: Int) {
        if (_state.compareAndSet(Idle, Loading) || _state.compareAndSet(Failed, Loading)) {
            try {
                charactersApi.getCharacters(page = page)
                    .onSuccess { response ->
                        charactersDao.insertOrUpdateCharacters(
                            characters = response.results.toDbModel()
                        )
                        charactersDao.insertOrUpdatePaginationInfo(
                            response.pagination.toDbModel(page = page)
                        )
                    }
                _state.value = Idle
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                _state.value = Failed
                Logger.e(e) { "failed to fetch and store characters for page $page" }
            }
        }
    }

    private fun List<CharacterApiModel>.toDbModel() = map { apiModel ->
        CharacterDbModel(
            name = apiModel.name,
            image = apiModel.image,
            status = apiModel.status,
            species = apiModel.species,
            type = apiModel.type,
            gender = apiModel.gender,
            id = apiModel.id,
        )
    }

    private fun Pagination.toDbModel(page: Int) = CharacterPaginationDbModel(
        currentPage = page,
        totalPages = pages,
    )

    private fun List<CharacterDbModel>.toDomainModel() = map { apiModel ->
        Character(
            name = apiModel.name,
            image = apiModel.image,
            status = when (apiModel.status) {
                "Alive" -> Status.Alive
                "Dead" -> Status.Dead
                else -> Status.Unknown
            },
            species = apiModel.species,
            type = apiModel.type,
            gender = when (apiModel.gender) {
                "Female" -> Gender.Female
                "Male" -> Gender.Male
                "Genderless" -> Gender.Genderless
                else -> Gender.Unknown
            },
            id = apiModel.id.toString(),
        )
    }
}