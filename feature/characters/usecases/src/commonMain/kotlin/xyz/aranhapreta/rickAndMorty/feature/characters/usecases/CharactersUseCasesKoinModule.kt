package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

import org.koin.dsl.module

val featureCharactersUseCasesKoinModule = module {
    factory<ObserveAllCharacters> {
        ObserveAllCharactersImpl(charactersRepository = get())
    }
    factory<ObserveCharactersLoadingState> {
        ObserveCharactersLoadingStateImpl(charactersRepository = get())
    }
    factory<LoadMoreCharacters> {
        LoadMoreCharactersImpl(charactersRepository = get())
    }
}