package xyz.aranhapreta.feature.episodes.api.contract

import xyz.aranhapreta.api.models.`in`.Episode
import xyz.aranhapreta.api.models.`in`.PaginatedApiResponse

interface EpisodeApi {
    suspend fun getEpisodes(
        page: Int? = null,
    ): PaginatedApiResponse<Episode>

    suspend fun getEpisode(id: Int): Episode
}
