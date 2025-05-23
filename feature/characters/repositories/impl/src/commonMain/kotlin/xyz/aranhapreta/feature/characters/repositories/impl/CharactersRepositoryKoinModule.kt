package xyz.aranhapreta.feature.characters.repositories.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository

val featureCharactersRepositoryKoinModule = module {
    single<CharactersRepository> {
        CharactersRepositoryImpl(
            charactersApi = get(),
            charactersDao = get()
        )
    }
}