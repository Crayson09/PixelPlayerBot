package dev.crayson.config

@JvmRecord
data class Config(
    val token: String = "TOKEN",
    val welcomeChannelId: Long,
    val joinRoles: List<Long> = listOf()
)
