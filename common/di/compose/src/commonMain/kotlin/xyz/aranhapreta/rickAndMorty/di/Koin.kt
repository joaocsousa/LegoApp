package xyz.aranhapreta.rickAndMorty.di

import androidx.compose.runtime.Composable
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.koin.compose.KoinApplication

private const val KoinTag = "koin"

@Composable
fun Koin(content: @Composable () -> Unit) {
    KoinApplication(
        application = {
            logger(KermitKoinLogger(Logger.withTag(KoinTag)))
            modules(koinModules)
        },
        content = content
    )
}
