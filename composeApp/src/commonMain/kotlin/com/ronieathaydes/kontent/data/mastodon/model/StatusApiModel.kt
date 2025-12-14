package com.ronieathaydes.kontent.data.mastodon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a status posted by an account.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Status/">Mastodon API Status</a>
 */
@Serializable
data class StatusApiModel(
    /**
     * HTML-encoded status content.
     */
    @SerialName(value = "content")
    val content: String,
)
