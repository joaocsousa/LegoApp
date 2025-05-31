package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureCharactersPresentationKoinModule = module {
    viewModelOf(::CharactersViewModel)
}