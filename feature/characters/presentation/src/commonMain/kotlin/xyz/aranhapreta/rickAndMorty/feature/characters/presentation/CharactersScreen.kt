package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rickandmorty.feature.characters.presentation.generated.resources.Res
import rickandmorty.feature.characters.presentation.generated.resources.gender_female
import rickandmorty.feature.characters.presentation.generated.resources.gender_genderless
import rickandmorty.feature.characters.presentation.generated.resources.gender_label
import rickandmorty.feature.characters.presentation.generated.resources.gender_male
import rickandmorty.feature.characters.presentation.generated.resources.gender_unknown
import rickandmorty.feature.characters.presentation.generated.resources.species_label
import rickandmorty.feature.characters.presentation.generated.resources.status_alive
import rickandmorty.feature.characters.presentation.generated.resources.status_dead
import rickandmorty.feature.characters.presentation.generated.resources.status_label
import rickandmorty.feature.characters.presentation.generated.resources.status_unknown
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterGender
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterScreenEvent
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterState
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterStatus
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharactersScreenState

@Composable
fun CharactersScreen(contentPadding: PaddingValues) {
    val viewModel = koinViewModel<CharactersViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Content(
        state = state.value,
        onEvent = viewModel::onEvent,
        contentPadding = contentPadding,
    )
}

@Composable
private fun Content(
    state: CharactersScreenState,
    onEvent: (CharacterScreenEvent) -> Unit,
    contentPadding: PaddingValues
) {
    when {
        state.characters.isNotEmpty() -> CharactersList(
            state = state,
            onEvent = onEvent,
            contentPadding = contentPadding,
        )

        state.isLoading -> Loading()
        state.error -> Text("ERROR")
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize().systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CharactersList(
    state: CharactersScreenState,
    onEvent: (CharacterScreenEvent) -> Unit,
    contentPadding: PaddingValues
) {
    val padding = 24.dp
    val topPadding = contentPadding.calculateTopPadding() + padding
    val bottomPadding = contentPadding.calculateBottomPadding() + padding
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .map { it.lastOrNull()?.index }
            .filterNotNull()
            .distinctUntilChanged()
            .collect { index ->
                onEvent(CharacterScreenEvent.ScrolledTo(index))
            }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = spacedBy(24.dp),
        contentPadding = PaddingValues(
            start = padding,
            top = topPadding,
            end = padding,
            bottom = bottomPadding
        ),
        state = listState,
    ) {
        items(state.characters) { item ->
            CharacterItem(
                modifier = Modifier.fillMaxWidth(),
                item = item,
                onEvent = onEvent,
            )
        }
        if (state.isLoading) {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun CharacterItem(
    modifier: Modifier,
    item: CharacterState,
    onEvent: (CharacterScreenEvent) -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = { onEvent(CharacterScreenEvent.CharacterClicked(item.id)) }
    ) {
        Row(
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.size(80.dp).clip(CircleShape),
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = statusLabel(item.status), style = MaterialTheme.typography.labelLarge)
                Text(text = genderLabel(item.gender), style = MaterialTheme.typography.labelLarge)
                Text(
                    text = speciesLabel(item.species, item.type),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun statusLabel(status: CharacterStatus): String {
    return stringResource(Res.string.status_label, status.label())
}

@Composable
private fun genderLabel(gender: CharacterGender): String {
    return stringResource(Res.string.gender_label, gender.label())
}

@Composable
private fun speciesLabel(species: String, type: String): String {
    val species = listOf(species, type).filter { it.isNotBlank() }.joinToString(", ")
    return stringResource(Res.string.species_label, species)
}

@Composable
private fun CharacterGender.label(): String {
    val resource = when (this) {
        CharacterGender.Female -> Res.string.gender_female
        CharacterGender.Genderless -> Res.string.gender_genderless
        CharacterGender.Male -> Res.string.gender_male
        CharacterGender.Unknown -> Res.string.gender_unknown
    }
    return stringResource(resource)
}

@Composable
private fun CharacterStatus.label(): String {
    val resource = when (this) {
        CharacterStatus.Alive -> Res.string.status_alive
        CharacterStatus.Dead -> Res.string.status_dead
        CharacterStatus.Unknown -> Res.string.status_unknown
    }
    return stringResource(resource)
}