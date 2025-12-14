package com.ronieathaydes.kontent.data.mastodon

import com.ronieathaydes.kontent.data.mastodon.model.StatusApiModel
import com.ronieathaydes.kontent.data.network.bodyAsResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MastodonRepository(
    private val httpClient: HttpClient = mastodonHttpClient,
) {
    suspend fun getPublicTimelines(): Result<List<StatusApiModel>> =
        httpClient.get(urlString = "v1/timelines/public")
            .bodyAsResult()
}
