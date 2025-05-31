package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Character
import xyz.aranhapreta.rickAndMorty.feature.characters.repositories.CharactersRepository

class ObserveAllCharactersImpl(
    private val charactersRepository: CharactersRepository
) : ObserveAllCharacters {
    override fun invoke(): Flow<List<Character>> {
        return charactersRepository.observeAllCharacters()
    }
}