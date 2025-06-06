package xyz.aranhapreta.rickAndMorty.database.models

data class CharacterDbModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
)
