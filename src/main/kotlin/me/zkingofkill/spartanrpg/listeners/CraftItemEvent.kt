package me.zkingofkill.spartanrpg.listeners

import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.objects.User
import me.zkingofkill.spartanrpg.objects.enums.ClassType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import kotlin.random.Random

class CraftItemEvent : Listener {

    private val knightCooldownList = arrayListOf<String>()

    /*
        Habilidade passiva
     */
    @EventHandler
    fun passiveSkillKnight(event: CraftItemEvent) {
        val player = event.whoClicked
        val user = User.get(player.name) as User
        val clazzType = user.clazzType
        val cf = SpartanRPG.singleton.config
        if (clazzType == ClassType.KNIGHT && event.recipe.result.type.name.contains("SWORD", true)) {
            val random = Random.nextDouble(100.0)
            if(random <= cf.classes.knightPassiveChance)
            if(!knightCooldownList.contains(player.name)){

            }
        }
    }
}