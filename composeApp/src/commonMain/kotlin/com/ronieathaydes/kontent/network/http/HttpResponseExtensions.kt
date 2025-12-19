package com.ronieathaydes.kontent.network.http

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
                Result.failure(HttpException(cause = cause))
            } catch (cause: DoubleReceiveException) {
                Result.failure(HttpException(cause = cause))
            }
        }

        else -> {
            Result.failure(
                HttpException(
                    message = "Request failed with ${status.value}.",
                ),
            )
        }
    }
