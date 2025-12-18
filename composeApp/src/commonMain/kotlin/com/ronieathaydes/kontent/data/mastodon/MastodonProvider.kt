package com.ronieathaydes.kontent.data.mastodon

import com.ronieathaydes.kontent.data.mastodon.timeline.TimelineRepository
import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.domain.provider.KontentProvider
import org.koin.core.annotation.Factory

@Factory
class MastodonProvider(
    private val timelineRepository: TimelineRepository,
) : KontentProvider {

    override suspend fun getHomeTimeline(): Result<List<Status>> =
        timelineRepository.getHomeTimeline()
}
