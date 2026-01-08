package com.ronieathaydes.kontent.data.mastodon.model.status

import com.ronieathaydes.kontent.data.mastodon.qualifier.MastodonHttpClient
import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.network.http.bodyAsResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Factory

@Factory
class StatusRepository(
    @MastodonHttpClient private val httpClient: HttpClient,
    private val statusApiModelMapper: StatusApiModelMapper,
) {
    suspend fun getHomeTimeline(): Result<List<Status>> =
        httpClient.get(urlString = "v1/timelines/home")
            .bodyAsResult<List<StatusApiModel>>()
            .map { statuses -> statuses.map(statusApiModelMapper::map) }
}
