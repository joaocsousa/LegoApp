package xyz.aranhapreta.feature.books.api.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.aranhapreta.api.models.`in`.Character
import xyz.aranhapreta.api.models.`in`.PaginatedApiResponse
import xyz.aranhapreta.feature.books.api.contract.CharactersApi

internal class CharacterApiImpl(
    private val client: HttpClient
) : CharactersApi {

    override suspend fun getCharacters(page: Int?): PaginatedApiResponse<Character> {
        return client.get("/character") {
            page?.let { parameter("page", it) }
        }.body()
    }

    override suspend fun getCharacter(id: Int): Character {
        return client.get("/character/$id").body()
    }
}