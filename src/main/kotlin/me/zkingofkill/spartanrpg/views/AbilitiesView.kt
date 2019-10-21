package me.zkingofkill.spartanrpg.views

import fr.minuskube.inv.content.InventoryContents
import fr.minuskube.inv.content.InventoryProvider
import org.bukkit.entity.Player
import fr.minuskube.inv.SmartInventory
import me.zkingofkill.spartanrpg.models.User
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import utils.ItemStackBuilder


class AbilitiesView(val player: Player) : InventoryProvider {
    private val INVENTORY: SmartInventory = SmartInventory.builder()
        .id(this.player.toString())
        .provider(this)
        .size(6, 9)
        .title("[Habilidades]")
        .build()

    override fun init(player: Player, contents: InventoryContents) {
        val user = User.get(player.displayName)
        val abilities = arrayListOf(user!!.abilities.pirate)
        val backItem =  ItemStackBuilder(Material.ARROW).setName("§c <--- Pagina anterior").build()
        val nextItem =  ItemStackBuilder(Material.ARROW).setName("§c ---> Proxima pagina").build()
        val disabledIem = ItemStackBuilder(Material.ARROW).setName("§c ---> Proxima pagina").build()
        for (ability in abilities) {
            val args = ability.properties.menuItem.split(":")
            val item = ItemStackBuilder(Material.valueOf(args[0].toUpperCase())).setDurability(args[1].toInt()).build()
        }
    }

    override fun update(player: Player, contents: InventoryContents) {

    }

    fun open() {
        this.INVENTORY.open(player)
    }

    fun close() {
        this.INVENTORY.close(player)
    }
}