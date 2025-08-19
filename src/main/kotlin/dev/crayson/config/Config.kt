package dev.crayson.config

@JvmRecord
data class Config(
    val token: String = "TOKEN",
    val welcomeChannelId: Long = 1,
    val joinRoles: List<Long> = listOf(1),
)
