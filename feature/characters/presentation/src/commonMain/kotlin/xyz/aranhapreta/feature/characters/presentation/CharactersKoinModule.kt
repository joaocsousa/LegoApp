package xyz.aranhapreta.feature.characters.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureCharactersPresentationKoinModule = module {
    viewModelOf(::CharactersViewModel)
}