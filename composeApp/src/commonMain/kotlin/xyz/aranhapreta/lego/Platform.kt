package xyz.aranhapreta.lego

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform