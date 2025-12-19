package com.ronieathaydes.kontent.network.qualifier

import org.koin.core.annotation.Named

@Named
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class DefaultHttpClient
