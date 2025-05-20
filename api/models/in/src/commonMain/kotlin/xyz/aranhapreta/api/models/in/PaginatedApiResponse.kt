package xyz.aranhapreta.api.models.`in`

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedApiResponse<T>(
    @SerialName("info")
    val pagination: Pagination,
    val results: List<T>
)