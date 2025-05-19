package xyz.aranhapreta.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedApiResponse<T>(
    val pagination: Pagination,
    val results: List<T>
)