package xyz.aranhapreta.feature.characters.presentation.models

internal sealed interface CharactersScreenState {
    data object Initial : CharactersScreenState
    data object Loading : CharactersScreenState
    data class Error(val message: String) : CharactersScreenState
    data class Success(val characters: List<CharacterState>) : CharactersScreenState
}
