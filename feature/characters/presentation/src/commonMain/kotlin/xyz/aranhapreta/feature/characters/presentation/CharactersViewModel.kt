package xyz.aranhapreta.feature.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.aranhapreta.feature.characters.entities.Character
import xyz.aranhapreta.feature.characters.presentation.models.CharacterScreenEvent
import xyz.aranhapreta.feature.characters.presentation.models.CharacterScreenEvent.CharacterClicked
import xyz.aranhapreta.feature.characters.presentation.models.CharacterState
import xyz.aranhapreta.feature.characters.presentation.models.CharactersScreenState
import xyz.aranhapreta.feature.characters.presentation.models.toState
import xyz.aranhapreta.feature.characters.usecases.contract.ObserveAllCharacters

internal class CharactersViewModel(
    private val observeAllCharacters: ObserveAllCharacters
) : ViewModel() {

    private val _state = MutableStateFlow<CharactersScreenState>(CharactersScreenState.Initial)
    val state = _state.stateIn(
        scope = viewModelScope,
        initialValue = CharactersScreenState.Initial,
        started = WhileSubscribed(stopTimeoutMillis = 5000)
    )

    init {
        viewModelScope.launch {
            _state.value = CharactersScreenState.Loading
            observeAllCharacters().collect {
                _state.value = CharactersScreenState.Success(characters = it.toState())
            }
        }
    }

    fun onEvent(event: CharacterScreenEvent) {
        when (event) {
            is CharacterClicked -> println("event ${event}")
        }
    }

    private fun List<Character>.toState() = map {
        CharacterState(
            id = it.id,
            name = it.name,
            imageUrl = it.image,
            status = it.status.toState(),
            species = it.species,
            type = it.type,
            gender = it.gender.toState(),
        )
    }
}