package com.ronieathaydes.kontent.data.mastodon.provider

import com.ronieathaydes.kontent.data.mastodon.settings.SettingsRepository
import com.ronieathaydes.kontent.data.mastodon.timeline.TimelineRepository
import com.ronieathaydes.kontent.domain.model.Config
import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.domain.provider.KontentProvider
import org.koin.core.annotation.Factory

@Factory
class MastodonKontentProvider(
    private val settingsRepository: SettingsRepository,
    private val timelineRepository: TimelineRepository,
) : KontentProvider {

    override suspend fun getConfigs(): List<Config> =
        settingsRepository.getSettings()

    override suspend fun getHomeTimeline(): Result<List<Status>> =
        timelineRepository.getHomeTimeline()
}
