package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState

interface ObserveCharactersLoadingState {
    operator fun invoke(): Flow<CharactersLoadingState>
}