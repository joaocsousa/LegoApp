package xyz.aranhapreta.feature.locations.api.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.aranhapreta.rickAndMorty.api.models.`in`.LocationApiModel
import xyz.aranhapreta.rickAndMorty.api.models.`in`.PaginatedReponseApiModel
import xyz.aranhapreta.feature.locations.api.contract.LocationApi

internal class LocationApiImpl(
    private val client: HttpClient
) : LocationApi {

    override suspend fun getLocations(page: Int?): PaginatedReponseApiModel<LocationApiModel> {
        return client.get("/location") {
            page?.let { parameter("page", it) }
        }.body()
    }

    override suspend fun getLocation(id: Int): LocationApiModel {
        return client.get("/location/$id").body()
    }
}
