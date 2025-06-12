package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.Character
import xyz.aranhapreta.rickAndMorty.feature.characters.entities.CharactersLoadingState
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterScreenEvent
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterScreenEvent.CharacterClicked
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterState
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharactersScreenState
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.toState
import xyz.aranhapreta.rickAndMorty.feature.characters.usecases.LoadMoreCharacters
import xyz.aranhapreta.rickAndMorty.feature.characters.usecases.ObserveAllCharacters
import xyz.aranhapreta.rickAndMorty.feature.characters.usecases.ObserveCharactersLoadingState

internal class CharactersViewModel(
    private val observeAllCharacters: ObserveAllCharacters,
    private val observeCharactersLoadingState: ObserveCharactersLoadingState,
    private val loadMoreCharacters: LoadMoreCharacters,
) : ViewModel() {

    private val _state = MutableStateFlow<CharactersScreenState>(CharactersScreenState())
    val state = _state.stateIn(
        scope = viewModelScope,
        initialValue = CharactersScreenState(),
        started = WhileSubscribed(stopTimeoutMillis = 5000)
    )

    init {
        viewModelScope.launch {
            observeAllCharacters.invoke()
                .onStart { emit(emptyList()) }
                .combine(observeCharactersLoadingState()) { characters, loadingState ->
                    characters to loadingState
                }
                .collect { (characters, loadingState) ->
                    Logger.d { "loaded ${characters.size} loadingState = $loadingState" }
                    _state.value = _state.value.copy(
                        isLoading = loadingState is CharactersLoadingState.Loading,
                        characters = characters.toState(),
                        error = loadingState is CharactersLoadingState.Failed,
                    )
                }
        }
    }

    fun onEvent(event: CharacterScreenEvent) {
        when (event) {
            is CharacterClicked -> Logger.d { "event $event" }
            is CharacterScreenEvent.ScrolledTo -> onScrolledTo(event.index)
        }
    }

    private fun onScrolledTo(index: Int) {
        val size = _state.value.characters.size
        Logger.d { "items = $size" }
        if (index >= size - 10) {
            viewModelScope.launch {
                Logger.d { "load more" }
                loadMoreCharacters()
            }
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