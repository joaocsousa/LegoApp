package xyz.aranhapreta.rickAndMorty.feature.characters.repositories

import co.touchlab.kermit.Logger
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import xyz.aranhapreta.rickAndMorty.api.models.`in`.CharacterApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginationApiModel
import xyz.aranhapreta.rickAndMorty.database.CharactersDao
import xyz.aranhapreta.rickAndMorty.database.models.CharacterDbModel
import xyz.aranhapreta.rickAndMorty.database.models.CharacterPaginationDbModel
import xyz.aranhapreta.rickAndMorty.feature.characters.api.CharactersApi
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Character
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState.Failed
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState.Idle
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState.Loading
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Gender
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Status
import kotlin.coroutines.coroutineContext

internal class CharactersRepositoryImpl(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao,
) : CharactersRepository {

    private val _state = MutableStateFlow<CharactersLoadingState>(Idle)

    override fun observeAllCharacters(): Flow<List<Character>> {
        return charactersDao
            .observeAllCharacters()
            .map { list -> list.toDomainModel() }
            .onStart {
                val count = charactersDao.countAll()
                if (count == 0) loadPage(1)
            }
    }

    override fun observeState(): Flow<CharactersLoadingState> = _state.asStateFlow()

    override suspend fun loadNextPage() {
        val currentPage = charactersDao.getPaginationInfo()?.currentPage ?: 1
        val totalPages = charactersDao.getPaginationInfo()?.totalPages ?: 1
        if (currentPage < totalPages) {
            loadPage(currentPage + 1)
        }
    }

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

    private fun PaginationApiModel.toDbModel(page: Int) = CharacterPaginationDbModel(
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