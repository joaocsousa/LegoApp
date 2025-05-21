package xyz.aranhapreta.feature.characters.usecases.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.characters.usecases.contract.LoadMoreCharacters
import xyz.aranhapreta.feature.characters.usecases.contract.ObserveAllCharacters
import xyz.aranhapreta.feature.characters.usecases.contract.ObserveCharactersLoadingState

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