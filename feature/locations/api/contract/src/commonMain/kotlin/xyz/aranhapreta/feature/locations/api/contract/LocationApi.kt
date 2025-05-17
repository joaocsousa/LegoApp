package xyz.aranhapreta.feature.locations.api.contract

import xyz.aranhapreta.api.models.`in`.Location
import xyz.aranhapreta.api.models.`in`.PaginatedApiResponse

interface LocationApi {
    suspend fun getLocations(
        page: Int? = null,
    ): PaginatedApiResponse<Location>

    suspend fun getLocation(id: Int): Location
}
