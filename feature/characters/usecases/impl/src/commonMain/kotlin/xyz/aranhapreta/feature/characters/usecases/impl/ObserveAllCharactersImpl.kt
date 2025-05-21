package xyz.aranhapreta.feature.characters.usecases.impl

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.Character
import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository
import xyz.aranhapreta.feature.characters.usecases.contract.ObserveAllCharacters

class ObserveAllCharactersImpl(
    private val charactersRepository: CharactersRepository
) : ObserveAllCharacters {
    override fun invoke(): Flow<List<Character>> {
        return charactersRepository.observeAllCharacters()
    }
}