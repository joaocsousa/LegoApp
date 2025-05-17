package xyz.aranhapreta.api.models.`in`

interface Pagination {
    val count: Int
    val pages: Int
    val next: String?
    val prev: String?
}