package com.ronieathaydes.kontent.network.di

import com.ronieathaydes.kontent.network.http.DefaultHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Configuration
class NetworkModule {

    @Single
    fun providesJson(): Json =
        Json {
            ignoreUnknownKeys = true
        }

    @Single
    @DefaultHttpClient
    fun providesDefaultHttpClient(json: Json): HttpClient =
        HttpClient {
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(json)
            }
        }
}
