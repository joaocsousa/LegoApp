package xyz.aranhapreta.feature.episodes.api.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.episodes.api.contract.EpisodeApi

val featureEpisodesApiKoinModule = module {
    single<EpisodeApi> {
        EpisodeApiImpl(client = get())
    }
}