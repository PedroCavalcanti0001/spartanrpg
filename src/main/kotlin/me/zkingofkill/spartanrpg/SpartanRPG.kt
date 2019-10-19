package me.zkingofkill.spartanrpg

import fr.minuskube.inv.InventoryManager
import me.zkingofkill.spartanrpg.commands.RpgCommand
import me.zkingofkill.spartanrpg.config.Config
import me.zkingofkill.spartanrpg.database.ConnectionFactory
import me.zkingofkill.spartanrpg.hooks.PlaceHolderAPI
import me.zkingofkill.spartanrpg.listeners.CraftItemEvent
import me.zkingofkill.spartanrpg.listeners.PlayerJoinEvent
import me.zkingofkill.spartanrpg.objects.User
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class SpartanRPG : JavaPlugin() {

    companion object {
        lateinit var singleton: SpartanRPG
    }
    lateinit var config: Config
    lateinit var inventoryManager: InventoryManager
    override fun onEnable() {
        singleton = this

        config = Config()

        inventoryManager = InventoryManager(this)
        inventoryManager.init()

        ConnectionFactory.init()
        PlaceHolderAPI().register()
        server.pluginManager.registerEvents(PlayerJoinEvent(), this)
        server.pluginManager.registerEvents(CraftItemEvent(), this)

        
        getCommand("rpg")?.setExecutor(RpgCommand())
    }


    override fun onDisable() {
        super.onDisable()
        User.saveAll()
    }


}