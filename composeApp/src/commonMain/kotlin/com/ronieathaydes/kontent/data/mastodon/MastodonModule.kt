package com.ronieathaydes.kontent.data.mastodon

import com.ronieathaydes.kontent.network.DefaultHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("com.ronieathaydes.kontent.data.mastodon")
class MastodonModule {

    @Single
    @MastodonHttpClient
    fun providesMastodonHttpClient(@DefaultHttpClient httpClient: HttpClient): HttpClient =
        httpClient.config {
            defaultRequest {
                url("https://mastodon.social/api/")
                header(HttpHeaders.Authorization, "<<your authorization token here>>")
            }
        }
}
