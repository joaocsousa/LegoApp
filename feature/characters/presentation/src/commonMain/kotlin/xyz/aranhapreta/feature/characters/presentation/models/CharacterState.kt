package xyz.aranhapreta.feature.characters.presentation.models

internal data class CharacterState(
    val id: String,
    val name: String,
    val imageUrl: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender
)