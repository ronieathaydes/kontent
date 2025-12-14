package com.ronieathaydes.kontent.data.network

import com.ronieathaydes.kontent.data.network.error.HttpGenericException
import com.ronieathaydes.kontent.data.network.error.HttpParseException
import io.ktor.client.call.DoubleReceiveException
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> HttpResponse.bodyAsResult(): Result<T> =
    when {
        status.isSuccess() -> {
            try {
                Result.success(body())
            } catch (cause: NoTransformationFoundException) {
                Result.failure(HttpParseException(cause = cause))
            } catch (cause: DoubleReceiveException) {
                Result.failure(HttpGenericException(cause = cause))
            }
        }

        else -> {
            Result.failure(
                HttpGenericException(
                    message = "Request failed with ${status.value}.",
                )
            )
        }
    }
