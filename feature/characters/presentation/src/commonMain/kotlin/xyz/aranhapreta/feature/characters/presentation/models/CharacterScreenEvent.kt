package xyz.aranhapreta.feature.characters.presentation.models

internal sealed interface CharacterScreenEvent {
    data class CharacterClicked(val id: String) : CharacterScreenEvent
    data class ScrolledTo(val index: Int) : CharacterScreenEvent
}