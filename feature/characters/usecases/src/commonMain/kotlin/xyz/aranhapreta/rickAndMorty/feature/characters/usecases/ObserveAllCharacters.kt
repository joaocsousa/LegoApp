package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Character

interface ObserveAllCharacters {
    operator fun invoke(): Flow<List<Character>>
}