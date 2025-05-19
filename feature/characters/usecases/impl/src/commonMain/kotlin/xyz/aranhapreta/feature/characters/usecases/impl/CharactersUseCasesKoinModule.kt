package xyz.aranhapreta.feature.characters.usecases.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.characters.usecases.contract.GetAllCharacters

val featureCharactersUseCasesKoinModule = module {
    factory<GetAllCharacters> {
        GetAllCharactersImpl(charactersRepository = get())
    }
}