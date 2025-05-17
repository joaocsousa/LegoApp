package xyz.aranhapreta.lego

import androidx.compose.runtime.Composable
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.koin.compose.KoinApplication
import xyz.aranhapreta.api.core.apiCoreModule
import xyz.aranhapreta.feature.books.api.impl.featureCharactersApiKoinModule
import xyz.aranhapreta.feature.episodes.api.impl.featureEpisodesApiKoinModule
import xyz.aranhapreta.feature.locations.api.impl.featureLocationsApiKoinModule

private const val KoinTag = "koin"

@Composable
internal fun Koin(content: @Composable () -> Unit) {
    KoinApplication(
        application = {
            logger(KermitKoinLogger(Logger.withTag(KoinTag)))
            modules(
                listOf(
                    apiCoreModule,
                    featureEpisodesApiKoinModule,
                    featureCharactersApiKoinModule,
                    featureLocationsApiKoinModule,
                )
            )
        },
        content = content
    )
}