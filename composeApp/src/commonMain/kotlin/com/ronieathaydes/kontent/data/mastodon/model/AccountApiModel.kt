package com.ronieathaydes.kontent.data.mastodon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user of Mastodon and their associated profile.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Account/">Mastodon API Account</a>
 */
@Serializable
data class AccountApiModel(
    /**
     * The WebFinger account URI. Equal to username for local users, or username@domain for remote users.
     */
    @SerialName(value = "acct")
    val acct: String,

    /**
     * The profile’s display name.
     */
    @SerialName(value = "display_name")
    val displayName: String,

    /**
     * An image icon that is shown next to statuses and in the profile.
     */
    @SerialName(value = "avatar")
    val avatar: String,
)
