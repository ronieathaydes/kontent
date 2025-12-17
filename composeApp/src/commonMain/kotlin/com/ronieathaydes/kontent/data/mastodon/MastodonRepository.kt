package com.ronieathaydes.kontent.data.mastodon

import com.ronieathaydes.kontent.data.mastodon.model.StatusApiModel
import com.ronieathaydes.kontent.network.bodyAsResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
class MastodonRepository(
    @Named("mastodon") private val httpClient: HttpClient,
) {
    suspend fun getPublicTimelines(): Result<List<StatusApiModel>> =
        httpClient.get(urlString = "v1/timelines/public")
            .bodyAsResult()
}
