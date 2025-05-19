package xyz.aranhapreta.feature.characters.repositories.contract

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.Character

interface CharactersRepository {
    fun getAllCharacters(): Flow<List<Character>>
}