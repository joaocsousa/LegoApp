package xyz.aranhapreta.rickAndMorty.di

import xyz.aranhapreta.rickAndMorty.database.databaseKoinModule
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.featureCharactersPresentationKoinModule
import xyz.aranhapreta.feature.episodes.api.impl.featureEpisodesApiKoinModule
import xyz.aranhapreta.feature.episodes.presentation.featureEpisodesPresentationKoinModule
import xyz.aranhapreta.feature.locations.api.impl.featureLocationsApiKoinModule
import xyz.aranhapreta.feature.locations.presentation.featureLocationsPresentationKoinModule
import xyz.aranhapreta.rickAndMorty.api.core.apiCoreModule
import xyz.aranhapreta.rickAndMorty.feature.characters.api.featureCharactersApiKoinModule
import xyz.aranhapreta.rickAndMorty.feature.characters.repositories.featureCharactersRepositoryKoinModule
import xyz.aranhapreta.rickAndMorty.feature.characters.usecases.featureCharactersUseCasesKoinModule

val koinModules = listOf(
    apiCoreModule,
    databaseKoinModule,
    featureEpisodesApiKoinModule,
    featureCharactersApiKoinModule,
    featureLocationsApiKoinModule,
    featureEpisodesPresentationKoinModule,
    featureLocationsPresentationKoinModule,
    featureCharactersPresentationKoinModule,
    featureCharactersRepositoryKoinModule,
    featureCharactersUseCasesKoinModule,
)
