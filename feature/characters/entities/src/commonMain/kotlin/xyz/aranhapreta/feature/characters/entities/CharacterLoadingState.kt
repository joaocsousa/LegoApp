package xyz.aranhapreta.feature.characters.entities

sealed interface CharacterLoadingState {
    data object Idle : CharacterLoadingState
    data object Loading : CharacterLoadingState
    data object Failed : CharacterLoadingState
}