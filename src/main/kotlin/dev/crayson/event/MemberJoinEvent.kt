package dev.crayson.event

import dev.crayson.DiscordBot
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.generics.getChannel
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent

fun JDA.registerJoinEvent() = listener<GuildMemberJoinEvent> {
    val member = it.member
    val guild = member.guild

    DiscordBot.instance.config.joinRoles.forEach { roleId ->
        val role = guild.getRoleById(roleId)
        if (role != null) {
            guild.addRoleToMember(member, role).queue()
        }
    }

    val welcomeChannel: TextChannel = guild.getChannel(DiscordBot.instance.config.welcomeChannelId) as TextChannel

    val welcomeMessage = "Welcome ${member.asMention}! We're glad to have you here."
    welcomeChannel.sendMessage(welcomeMessage).queue()
}