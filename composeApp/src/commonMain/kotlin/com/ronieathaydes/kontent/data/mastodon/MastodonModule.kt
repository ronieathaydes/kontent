package com.ronieathaydes.kontent.data.mastodon

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("com.ronieathaydes.kontent.data.mastodon")
class MastodonModule {

    @Single
    @Named("mastodon")
    fun providesMastodonHttpClient(@Named("default") httpClient: HttpClient): HttpClient =
        httpClient.config {
            defaultRequest {
                url("https://mastodon.social/api/")
            }
        }
}
