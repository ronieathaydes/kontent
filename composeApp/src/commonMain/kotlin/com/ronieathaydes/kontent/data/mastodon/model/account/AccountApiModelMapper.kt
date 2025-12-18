package com.ronieathaydes.kontent.data.mastodon.model.account

import com.ronieathaydes.kontent.domain.model.Account
import org.koin.core.annotation.Factory

@Factory
class AccountApiModelMapper {
    fun map(model: AccountApiModel): Account =
        Account(
            username = model.acct,
            displayName = model.displayName,
        )
}
