package xyz.aranhapreta.feature.locations.api.impl

import org.koin.dsl.module
import xyz.aranhapreta.feature.locations.api.contract.LocationApi

val featureLocationsApiKoinModule = module {
    single<LocationApi> {
        LocationApiImpl(client = get())
    }
}