package xyz.aranhapreta.feature.locations.api.contract

import xyz.aranhapreta.rickAndMorty.api.models.`in`.LocationApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginatedReponseApiModel

interface LocationApi {
    suspend fun getLocations(
        page: Int? = null,
    ): PaginatedReponseApiModel<LocationApiModel>

    suspend fun getLocation(id: Int): LocationApiModel
}
