package me.zkingofkill.spartanrpg.abilities

import me.zkingofkill.spartanrpg.SpartanRPG
import org.bukkit.event.Event
import org.bukkit.event.Listener

interface Ability<AbilityFile, Event> : Listener {
    var enable: Boolean
    var level: Int
    var lastActivation: Long
    val properties: AbilityFile
    fun event(event: Event)

    companion object {
        fun registerAbilitiesListeners() {
            val pl = SpartanRPG.singleton
            arrayListOf(Pirate()).forEach { pl.server.pluginManager.registerEvents(it, pl) }
        }
    }
}