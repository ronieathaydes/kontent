package com.ronieathaydes.kontent.data.mastodon.model

import com.ronieathaydes.kontent.domain.model.Status
import org.koin.core.annotation.Factory

@Factory
class StatusApiModelMapper(
    private val accountApiModelMapper: AccountApiModelMapper,
) {
    fun map(model: StatusApiModel): Status =
        Status(
            content = model.content.takeIf { it.isNotEmpty() },
            author = accountApiModelMapper.map(model.account),
            sharedStatus = model.reblog?.let(::map),
        )
}
