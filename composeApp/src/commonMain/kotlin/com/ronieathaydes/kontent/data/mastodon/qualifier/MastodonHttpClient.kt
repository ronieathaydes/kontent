package com.ronieathaydes.kontent.data.mastodon.qualifier

import org.koin.core.annotation.Named

@Named
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class MastodonHttpClient
