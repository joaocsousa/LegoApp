package xyz.aranhapreta.rickAndMorty.feature.characters.repositories

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Character
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState

interface CharactersRepository {
    fun observeAllCharacters(): Flow<List<Character>>
    fun observeState(): Flow<CharactersLoadingState>
    suspend fun loadNextPage()
}