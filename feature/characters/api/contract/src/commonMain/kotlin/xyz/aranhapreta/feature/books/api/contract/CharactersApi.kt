package xyz.aranhapreta.feature.books.api.contract

import xyz.aranhapreta.api.models.`in`.Character
import xyz.aranhapreta.api.models.`in`.PaginatedApiResponse

interface CharactersApi {
    suspend fun getCharacters(
        page: Int? = null,
    ): PaginatedApiResponse<Character>

    suspend fun getCharacter(id: Int): Character
}