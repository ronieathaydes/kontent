@file:Suppress("MatchingDeclarationName")

package com.ronieathaydes.kontent

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()
