package dev.crayson

import de.exlll.configlib.YamlConfigurationProperties
import de.exlll.configlib.YamlConfigurationStore
import dev.crayson.config.Config
import dev.crayson.config.serializer.KotlinStringSerializer
import dev.crayson.event.registerJoinEvent
import dev.minn.jda.ktx.jdabuilder.light
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import revxrsal.commands.Lamp
import revxrsal.commands.jda.JDALamp
import revxrsal.commands.jda.JDAVisitors
import revxrsal.commands.jda.actor.SlashCommandActor
import java.io.File

fun main(args: Array<String>) {
    DiscordBot()
}

class DiscordBot {

    companion object {
        lateinit var instance: DiscordBot
    }

    private val configFile: File = File("./run", "config.yml")

    private var configStore: YamlConfigurationStore<Config> = YamlConfigurationStore(
        Config::class.java,
        YamlConfigurationProperties
            .newBuilder()
            .addSerializer(java.lang.String::class.java, KotlinStringSerializer())
            .build()
    )

    var config: Config = configStore.update(configFile.toPath())

    val jda = light(config.token,true) {
        setEnabledIntents(GatewayIntent.entries)
        setMemberCachePolicy(MemberCachePolicy.ALL)
        setChunkingFilter(ChunkingFilter.ALL)
    }

    private val lamp: Lamp<SlashCommandActor> = JDALamp.builder<SlashCommandActor>().build()

    init {
        instance = this

        jda.apply {
            registerJoinEvent()
        }

        lamp.accept(JDAVisitors.slashCommands(jda))
    }
}