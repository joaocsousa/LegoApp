package xyz.aranhapreta.feature.books.api.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.books.api.contract.CharactersApi

val featureCharactersApiKoinModule = module {
    single<CharactersApi> {
        CharacterApiImpl(client = get())
    }
}