package xyz.aranhapreta.feature.characters.usecases.contract

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState

interface ObserveCharactersLoadingState {
    operator fun invoke(): Flow<CharacterLoadingState>
}