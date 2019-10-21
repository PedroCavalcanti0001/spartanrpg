package me.zkingofkill.spartanrpg.abilities

import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.config.files.abilities.Pirate
import me.zkingofkill.spartanrpg.models.User
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent

class Pirate(
    override var enable: Boolean = false,
    override var level: Int = 0,
    override var lastActivation: Long = 0
) : Ability<Pirate, BlockBreakEvent> {

    @EventHandler
    override fun event(event: BlockBreakEvent) {
        val user = User(event.player.name)
        val ability = user.abilities.pirate
        if (ability.enable) {

        }
    }

    override val properties: Pirate
        get() = SpartanRPG.singleton.config.pirate
}