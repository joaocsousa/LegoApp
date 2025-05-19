package xyz.aranhapreta.feature.characters.usecases.contract

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.Character

interface GetAllCharacters {
    operator fun invoke(): Flow<List<Character>>
}