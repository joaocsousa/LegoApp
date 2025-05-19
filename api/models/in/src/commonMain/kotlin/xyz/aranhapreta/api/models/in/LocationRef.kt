package xyz.aranhapreta.api.models.`in`

import kotlinx.serialization.Serializable

@Serializable
data class LocationRef(
    val name: String,
    val url: String
)