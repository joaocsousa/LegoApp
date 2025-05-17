package xyz.aranhapreta.feature.episodes.api.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.aranhapreta.api.models.`in`.Episode
import xyz.aranhapreta.api.models.`in`.PaginatedApiResponse
import xyz.aranhapreta.feature.episodes.api.contract.EpisodeApi

internal class EpisodeApiImpl(
    private val client: HttpClient
) : EpisodeApi {

    override suspend fun getEpisodes(page: Int?): PaginatedApiResponse<Episode> {
        return client.get("/episode") {
            page?.let { parameter("page", it) }
        }.body()
    }

    override suspend fun getEpisode(id: Int): Episode {
        return client.get("/episode/$id").body()
    }
}
