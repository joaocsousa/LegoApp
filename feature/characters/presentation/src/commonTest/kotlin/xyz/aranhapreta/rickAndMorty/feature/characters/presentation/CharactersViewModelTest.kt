package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import xyz.aranhapreta.rickAndMorty.di.koinModules
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest : KoinTest {

    private val sut by inject<CharactersViewModel>()

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(koinModules)
        }
    }

    @Test
    fun `test CharactersViewModel initialization`() {
        runTest(UnconfinedTestDispatcher()) {
            sut.state.test {
                println(awaitItem())
            }
        }
    }

}