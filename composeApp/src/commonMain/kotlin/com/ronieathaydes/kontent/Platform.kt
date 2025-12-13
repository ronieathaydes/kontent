package com.ronieathaydes.kontent

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
