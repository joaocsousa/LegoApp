package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

import kotlinx.coroutines.flow.Flow
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState
import xyz.aranhapreta.rickAndMorty.feature.characters.repositories.CharactersRepository

class ObserveCharactersLoadingStateImpl(
    private val charactersRepository: CharactersRepository
) : ObserveCharactersLoadingState {
    override fun invoke(): Flow<CharactersLoadingState> {
        return charactersRepository.observeState()
    }
}