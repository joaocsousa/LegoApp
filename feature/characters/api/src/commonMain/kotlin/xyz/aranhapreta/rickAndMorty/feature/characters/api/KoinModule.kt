package xyz.aranhapreta.rickAndMorty.feature.characters.api

import org.koin.dsl.module

val featureCharactersApiKoinModule = module {
    single<CharactersApi> {
        CharactersApiImpl(client = get())
    }
}