package dev.crayson.config

@JvmRecord
data class Config(
    val token: String = "TOKEN",
    val welcomeChannelId: String = "welcome-channel-id",
)
