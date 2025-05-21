package xyz.aranhapreta.feature.characters.repositories.contract

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.Character
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState

interface CharactersRepository {
    fun observeAllCharacters(): Flow<List<Character>>
    fun observeState(): Flow<CharacterLoadingState>
    suspend fun loadNextPage()
}