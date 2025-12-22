package com.ronieathaydes.kontent.data.mastodon.provider

import com.ronieathaydes.kontent.data.mastodon.model.config.ConfigRepository
import com.ronieathaydes.kontent.data.mastodon.model.status.StatusRepository
import com.ronieathaydes.kontent.domain.model.Settings
import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.domain.provider.KontentProvider
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.settings_mastodon_title
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.Factory

@Factory
class MastodonKontentProvider(
    private val configRepository: ConfigRepository,
    private val statusRepository: StatusRepository,
) : KontentProvider {

    override suspend fun getSettings(): Settings =
        Settings(
            title = getString(resource = Res.string.settings_mastodon_title),
            configs = configRepository.getConfigs(),
        )

    override suspend fun getHomeTimeline(): Result<List<Status>> =
        statusRepository.getHomeTimeline()
}
