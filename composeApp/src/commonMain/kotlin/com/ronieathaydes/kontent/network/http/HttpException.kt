package com.ronieathaydes.kontent.network.http

class HttpException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(message, cause)
