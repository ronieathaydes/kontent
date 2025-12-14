package com.ronieathaydes.kontent.data.network.error

class HttpParseException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(message, cause)
