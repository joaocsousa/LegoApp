package xyz.aranhapreta.features.locations.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LocationsScreen() {
    val viewModel = koinViewModel<LocationsViewModel>()
    Box { Text("Locations") }
}