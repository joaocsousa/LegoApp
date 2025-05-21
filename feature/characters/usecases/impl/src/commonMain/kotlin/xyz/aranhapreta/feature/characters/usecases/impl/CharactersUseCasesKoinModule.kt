package xyz.aranhapreta.feature.characters.usecases.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.characters.usecases.contract.ObserveAllCharacters

val featureCharactersUseCasesKoinModule = module {
    factory<ObserveAllCharacters> {
        ObserveAllCharactersImpl(charactersRepository = get())
    }
}