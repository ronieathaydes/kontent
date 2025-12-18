package com.ronieathaydes.kontent.network.http

import org.koin.core.annotation.Named

@Named
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class DefaultHttpClient
