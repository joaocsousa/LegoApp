package xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models

import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Gender

internal sealed interface CharacterGender {
    data object Male : CharacterGender
    data object Female : CharacterGender
    data object Genderless : CharacterGender
    data object Unknown : CharacterGender
}

internal fun Gender.toState() = when (this) {
    Gender.Female -> CharacterGender.Female
    Gender.Male -> CharacterGender.Male
    Gender.Genderless -> CharacterGender.Genderless
    Gender.Unknown -> CharacterGender.Unknown
}