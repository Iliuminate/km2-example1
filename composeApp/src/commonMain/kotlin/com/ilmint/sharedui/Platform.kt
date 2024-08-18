package com.ilmint.sharedui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform