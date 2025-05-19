package xyz.aranhapreta.feature.characters.usecases.impl

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.Character
import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository
import xyz.aranhapreta.feature.characters.usecases.contract.GetAllCharacters

class GetAllCharactersImpl(
    private val charactersRepository: CharactersRepository
) : GetAllCharacters {
    override fun invoke(): Flow<List<Character>> {
        return charactersRepository.getAllCharacters()
    }
}