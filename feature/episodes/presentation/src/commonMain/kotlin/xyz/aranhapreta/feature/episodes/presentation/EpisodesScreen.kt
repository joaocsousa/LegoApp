package xyz.aranhapreta.feature.episodes.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodesScreen() {
    val viewModel = koinViewModel<EpisodesViewModel>()
    Box { Text("Episodes") }
}