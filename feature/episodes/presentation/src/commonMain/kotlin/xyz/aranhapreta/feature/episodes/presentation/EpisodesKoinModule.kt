package xyz.aranhapreta.feature.episodes.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureEpisodesPresentationKoinModule = module {
    viewModelOf(::EpisodesViewModel)
}