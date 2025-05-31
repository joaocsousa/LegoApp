package xyz.aranhapreta.feature.locations.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureLocationsPresentationKoinModule = module {
    viewModelOf(::LocationsViewModel)
}