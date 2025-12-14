package com.ronieathaydes.kontent.data.mastodon

import com.ronieathaydes.kontent.data.network.defaultJson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

val mastodonHttpClient = HttpClient {
    defaultRequest {
        url("https://mastodon.social/api/")
        contentType(ContentType.Application.Json)
    }

    install(ContentNegotiation) {
        json(defaultJson)
    }
}
