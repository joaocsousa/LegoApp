package xyz.aranhapreta.feature.characters.usecases.impl

import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository
import xyz.aranhapreta.feature.characters.usecases.contract.LoadMoreCharacters

class LoadMoreCharactersImpl(
    private val charactersRepository: CharactersRepository
) : LoadMoreCharacters {
    override suspend fun invoke() {
        charactersRepository.loadNextPage()
    }
}