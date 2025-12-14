package com.ronieathaydes.kontent.data.network.error

class HttpException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(message, cause)
