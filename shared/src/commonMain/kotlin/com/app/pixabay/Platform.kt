package com.app.pixabay

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform