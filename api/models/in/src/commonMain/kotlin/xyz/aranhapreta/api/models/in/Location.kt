package xyz.aranhapreta.api.models.`in`

interface Location {
    val id: Int
    val name: String
    val type: String
    val dimension: String
    val residents: List<String>
    val url: String
    val created: String
}