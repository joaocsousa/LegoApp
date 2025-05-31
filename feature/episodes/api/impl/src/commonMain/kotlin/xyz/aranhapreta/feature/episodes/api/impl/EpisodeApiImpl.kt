package xyz.aranhapreta.feature.episodes.api.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.aranhapreta.rickAndMorty.api.models.`in`.EpisodeApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginatedReponseApiModel
import xyz.aranhapreta.feature.episodes.api.contract.EpisodeApi

internal class EpisodeApiImpl(
    private val client: HttpClient
) : EpisodeApi {

    override suspend fun getEpisodes(page: Int?): PaginatedReponseApiModel<EpisodeApiModel> {
        return client.get("/episode") {
            page?.let { parameter("page", it) }
        }.body()
    }

    override suspend fun getEpisode(id: Int): EpisodeApiModel {
        return client.get("/episode/$id").body()
    }
}
