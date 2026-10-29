package com.example.webvar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform