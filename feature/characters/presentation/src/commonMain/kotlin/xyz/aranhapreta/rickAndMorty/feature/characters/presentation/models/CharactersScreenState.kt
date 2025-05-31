package xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models

internal data class CharactersScreenState(
    val isLoading: Boolean = false,
    val characters: List<CharacterState> = emptyList<CharacterState>(),
    val error: Boolean = false,
)
