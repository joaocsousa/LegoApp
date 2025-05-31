package xyz.aranhapreta.feature.episodes.api.contract

import xyz.aranhapreta.rickAndMorty.api.models.`in`.EpisodeApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginatedReponseApiModel

interface EpisodeApi {
    suspend fun getEpisodes(
        page: Int? = null,
    ): PaginatedReponseApiModel<EpisodeApiModel>

    suspend fun getEpisode(id: Int): EpisodeApiModel
}
