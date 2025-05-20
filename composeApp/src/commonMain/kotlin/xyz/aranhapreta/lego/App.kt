package xyz.aranhapreta.lego

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.aranhapreta.feature.characters.presentation.CharactersScreen
import xyz.aranhapreta.feature.episodes.presentation.EpisodesScreen
import xyz.aranhapreta.features.locations.presentation.LocationsScreen
import xyz.aranhapreta.theme.AppTheme

@Composable
fun App() {
    Koin {
        AppTheme {
            val navController = rememberNavController()
            val startDestination = Screen.entries.first()
            var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        Screen.entries.forEachIndexed { index, destination ->
                            NavigationBarItem(
                                selected = selectedDestination == index,
                                onClick = {
                                    navController.navigate(route = destination.name)
                                    selectedDestination = index
                                },
                                icon = {},
                                label = { Text(destination.name) }
                            )
                        }
                    }
                }
            ) { contentPadding ->
                AppNavHost(
                    navController = navController,
                    startDestination = startDestination,
                    modifier = Modifier.padding(contentPadding)
                )
            }
        }
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.name
    ) {
        Screen.entries.forEach { destination ->
            composable(destination.name) {
                when (destination) {
                    Screen.Characters -> CharactersScreen()
                    Screen.Locations -> LocationsScreen()
                    Screen.Episodes -> EpisodesScreen()
                }
            }
        }
    }
}
