package xyz.aranhapreta.feature.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.aranhapreta.feature.characters.usecases.contract.GetAllCharacters

internal class CharactersViewModel(
    private val getAllCharacters: GetAllCharacters
) : ViewModel() {
    init {
        viewModelScope.launch {
            getAllCharacters()
                .stateIn(
                    viewModelScope,
                    initialValue = emptyList(),
                    started = WhileSubscribed(stopTimeoutMillis = 5000)
                )
                .collect {
                    println(it)
                }
        }
    }
}