package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

import xyz.aranhapreta.rickAndMorty.feature.characters.repositories.CharactersRepository

class LoadMoreCharactersImpl(
    private val charactersRepository: CharactersRepository
) : LoadMoreCharacters {
    override suspend fun invoke() {
        charactersRepository.loadNextPage()
    }
}