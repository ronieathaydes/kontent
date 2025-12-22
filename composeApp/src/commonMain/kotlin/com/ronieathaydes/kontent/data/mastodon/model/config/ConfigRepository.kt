package com.ronieathaydes.kontent.data.mastodon.model.config

import com.ronieathaydes.kontent.domain.model.Config
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.settings_mastodon_config_access_token_description
import com.ronieathaydes.kontent.resources.settings_mastodon_config_access_token_name
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.Factory

@Factory
class ConfigRepository {
    suspend fun getConfigs(): List<Config> =
        listOf(
            Config(
                name = getString(resource = Res.string.settings_mastodon_config_access_token_name),
                key = ConfigKeys.ACCESS_TOKEN_KEY,
                value = "",
                description = getString(resource = Res.string.settings_mastodon_config_access_token_description),
            ),
        )
}
