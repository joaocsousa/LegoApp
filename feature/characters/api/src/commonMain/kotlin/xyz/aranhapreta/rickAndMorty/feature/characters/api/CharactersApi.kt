package xyz.aranhapreta.rickAndMorty.feature.characters.api

import xyz.aranhapreta.rickAndMorty.api.models.`in`.CharacterApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginatedReponseApiModel

interface CharactersApi {
    suspend fun getCharacters(page: Int?): Result<PaginatedReponseApiModel<CharacterApiModel>>
    suspend fun getCharacter(id: Int): Result<CharacterApiModel>
}