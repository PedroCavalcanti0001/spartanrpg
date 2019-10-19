package me.zkingofkill.spartanrpg.views

import fr.minuskube.inv.ClickableItem
import fr.minuskube.inv.SmartInventory
import fr.minuskube.inv.content.InventoryContents
import fr.minuskube.inv.content.InventoryProvider
import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.objects.User
import me.zkingofkill.spartanrpg.objects.enums.ClassType
import me.zkingofkill.spartanrpg.objects.enums.MenuType
import org.bukkit.entity.Player

class ClassSelector(var player: Player) : InventoryProvider {
    val menu = SpartanRPG.singleton.config.guis.menu(MenuType.CLASS_SELECTOR)
    val inventory: SmartInventory = SmartInventory.builder()
        .size(menu.rows, menu.columns)
        .title(menu.title)
        .provider(this)
        .build()

    override fun init(player: Player, contents: InventoryContents) {
        val user = User(player.name)
        for (item in menu.items) {
            contents.set(item.row, item.column, ClickableItem.of(item.ItemStack) {
                user.clazzType = ClassType.valueOf(item.id.toUpperCase())
                User.users.add(user)
                close()
                println(User.users)
            })
        }
    }

    override fun update(player: Player, contents: InventoryContents?) {

    }


    fun open() {
        inventory.isCloseable = false
        inventory.open(player)
    }

    fun close() {
        inventory.isCloseable = false
        inventory.close(player)
    }


}