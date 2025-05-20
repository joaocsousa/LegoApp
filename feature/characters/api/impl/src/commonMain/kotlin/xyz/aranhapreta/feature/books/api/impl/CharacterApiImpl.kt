package xyz.aranhapreta.feature.books.api.impl

import co.touchlab.kermit.Logger
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

    override suspend fun getCharacters(page: Int?): Result<PaginatedApiResponse<Character>> {
        return runCatching<CharacterApiImpl, PaginatedApiResponse<Character>> {
            client.get("character") {
                page?.let { parameter("page", it) }
            }.body()
        }.onSuccess {
            Logger.i { "Received ${it.results.size} characters" }
        }.onFailure {
            Logger.w(throwable = it) { "error getting characters" }
        }
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        return runCatching {
            client.get("character/$id").body<Character>()
        }.onSuccess {
            Logger.i { "Received character $it" }
        }.onFailure {
            Logger.w(throwable = it) { "error getting character $id" }
        }
    }
}
