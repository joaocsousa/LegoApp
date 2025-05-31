package xyz.aranhapreta.rickAndMorty.feature.characters.entities

sealed interface CharactersLoadingState {
    data object Idle : CharactersLoadingState
    data object Loading : CharactersLoadingState
    data object Failed : CharactersLoadingState
}