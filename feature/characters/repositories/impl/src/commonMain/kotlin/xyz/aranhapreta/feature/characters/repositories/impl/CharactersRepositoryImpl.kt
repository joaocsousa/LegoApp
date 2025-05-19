package xyz.aranhapreta.feature.characters.repositories.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import xyz.aranhapreta.api.models.`in`.PaginatedApiResponse
import xyz.aranhapreta.feature.books.api.contract.CharactersApi
import xyz.aranhapreta.feature.characters.entities.Character
import xyz.aranhapreta.feature.characters.entities.Gender
import xyz.aranhapreta.feature.characters.entities.Status
import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository
import xyz.aranhapreta.api.models.`in`.Character as CharacterApiModel

internal class CharactersRepositoryImpl(
    private val charactersApi: CharactersApi
) : CharactersRepository {
    override fun getAllCharacters(): Flow<List<Character>> {
        return flow<PaginatedApiResponse<CharacterApiModel>> { charactersApi.getCharacters() }
            .map { it.results }
            .map { it.toDomainModel() }
    }

    private fun List<CharacterApiModel>.toDomainModel() = map { apiModel ->
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

            }
        )
    }
}