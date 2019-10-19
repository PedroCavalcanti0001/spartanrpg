package me.zkingofkill.spartanrpg.listeners

import me.zkingofkill.spartanrpg.objects.User
import me.zkingofkill.spartanrpg.views.ClassSelector
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinEvent : Listener {

    @EventHandler
    fun onFirstJoin(e: PlayerJoinEvent) {
        val user = User.get(e.player.name)
        if (user == null) ClassSelector(e.player).open()
        println(user)
    }

}