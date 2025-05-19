package xyz.aranhapreta.feature.characters.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersScreen() {
    val viewModel = koinViewModel<CharactersViewModel>()
    Box { Text("Characters") }
}

@Composable
@Preview
private fun PreviewCharactersScreen() {

}