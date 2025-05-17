package xyz.aranhapreta.api.models.`in`

interface PaginatedApiResponse<T> {
    val pagination: Pagination
    val results: List<T>
}