package com.ronieathaydes.kontent.domain.model

data class Status(
    val content: String?,
    val author: Account,
    val sharedStatus: Status?,
)
