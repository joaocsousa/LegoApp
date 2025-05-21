package xyz.aranhapreta.feature.characters.usecases.impl

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.feature.characters.entities.CharacterLoadingState
import xyz.aranhapreta.feature.characters.repositories.contract.CharactersRepository
import xyz.aranhapreta.feature.characters.usecases.contract.ObserveCharactersLoadingState

class ObserveCharactersLoadingStateImpl(
    private val charactersRepository: CharactersRepository
) : ObserveCharactersLoadingState {
    override fun invoke(): Flow<CharacterLoadingState> {
        return charactersRepository.observeState()
    }
}