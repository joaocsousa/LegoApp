package xyz.aranhapreta.rickAndMorty.feature.characters.repositories

import org.koin.dsl.module

val featureCharactersRepositoryKoinModule = module {
    single<CharactersRepository> {
        CharactersRepositoryImpl(
            charactersApi = get(),
            charactersDao = get()
        )
    }
}