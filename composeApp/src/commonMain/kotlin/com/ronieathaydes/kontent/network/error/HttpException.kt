package com.ronieathaydes.kontent.network.error

class HttpException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(message, cause)
