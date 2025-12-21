package com.ronieathaydes.kontent.data.mastodon.settings

import com.ronieathaydes.kontent.domain.model.Config
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.mastodon_config_access_token_description
import com.ronieathaydes.kontent.resources.mastodon_config_access_token_name
import org.koin.core.annotation.Factory

@Factory
class SettingsRepository {
    fun getSettings(): List<Config> =
        listOf(
            Config(
                name = Res.string.mastodon_config_access_token_name,
                key = SettingsKeys.ACCESS_TOKEN_KEY,
                value = "",
                description = Res.string.mastodon_config_access_token_description,
            ),
        )
}
