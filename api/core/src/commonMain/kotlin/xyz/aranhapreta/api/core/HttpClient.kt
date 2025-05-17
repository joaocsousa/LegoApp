package xyz.aranhapreta.api.core

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun json() = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
    explicitNulls = false
}

internal fun httpClient(baseUrl: String, json: Json) =
    HttpClient(httpEngineFactory) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d {
                        message
                    }
                }
            }
        }
        defaultRequest {
            header("Content-Type", "application/json")
            url(baseUrl)
        }
        install(ContentNegotiation) {
            json(json)
        }
    }

expect val httpEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>