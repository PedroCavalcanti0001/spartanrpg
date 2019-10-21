package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.models.config.ConfigItem
import me.zkingofkill.spartanrpg.models.config.ConfigMenu
import me.zkingofkill.spartanrpg.SpartanRPG
import org.bukkit.Material
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue
import utils.ItemStackBuilder

@Config(path = "Guis.yml")
class Guis : ConfigBuilder() {

    @ConfigValue(path = "classSelector.title")
    private var classSelectorTitle = "[Selecionar classe]"

    @ConfigValue(path = "classSelector.size.rows")
    private var classSelectorRows = 3

    @ConfigValue(path = "classSelector.size.columns")
    private var classSelectorColumns = 9

    @ConfigValue(path = "classSelector.items.archer.item")
    private var classSelectorItem = "1:1"

    @ConfigValue(path = "classSelector.items.archer.name")
    private var classSelectorItemName = "1:1"

    @ConfigValue(path = "classSelector.items.archer.lore")
    private var classSelectorItemLore = arrayListOf("&a* lore")

    @ConfigValue(path = "classSelector.items.archer.position.row")
    private var classSelectorItemPositionRow = 1

    @ConfigValue(path = "classSelector.items.archer.position.column")
    private var classSelectorItemPositionColumn = 1
/*
    fun menu(menuType: MenuType): ConfigMenu {
        val path = when (menuType) {
            MenuType.CLASS_SELECTOR -> "classSelector"
        }

        val title = fileConfiguration.getString("$path.title")!!
        val rows = fileConfiguration.getInt("$path.size.rows")
        val columns = fileConfiguration.getInt("$path.size.columns")
        val items = arrayListOf<ConfigItem>()

        for (section in fileConfiguration.getConfigurationSection("$path.items")!!.getKeys(false)) {
            val itemName = fileConfiguration.getString("$path.items.$section.name")
            val itemId = fileConfiguration.getString("$path.items.$section.item")!!.split(":")[0]
            val itemDur = fileConfiguration.getString("$path.items.$section.item")!!.split(":")[1].toInt()
            val itemLore = fileConfiguration.getStringList("$path.items.$section.lore")
            val itemRow = fileConfiguration.getInt("$path.items.$section.position.row")
            val itemColumn = fileConfiguration.getInt("$path.items.$section.position.column")
            val itemStack =
                ItemStackBuilder(Material.getMaterial(itemId)).setName(itemName).setDurability(itemDur).setLore(itemLore).build()
            val item = ConfigItem(
                id = section,
                ItemStack = itemStack,
                row = itemRow,
                column = itemColumn
            )
            items.add(item)
        }
        return ConfigMenu(rows, columns, items, title)
    }
 */

    fun init() {
        super.init(SpartanRPG.singleton, this)
    }
}




