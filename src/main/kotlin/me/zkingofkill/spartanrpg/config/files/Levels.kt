package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.models.config.ConfigLevel
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "Levels.yml")
class Levels : ConfigBuilder() {

    @ConfigValue(path = "levels.1")
    private var level = "EXP:350-POINTS:1"






    fun levels(): List<ConfigLevel> {
        val levels = arrayListOf<ConfigLevel>()
        for (sec in fileConfiguration.getConfigurationSection("levels")!!.getKeys(false)) {
            val lev = sec.toInt()
            val args = fileConfiguration.getString("levels.$sec")!!.split("-")
            val exp = args[0].split(":")[1].toDouble()
            val points = args[1].split(":")[1].toInt()
            val level = ConfigLevel(lev, exp, points)
            levels.add(level)
        }
        return levels
    }

    fun init() {
        super.init(SpartanRPG.singleton, this)
    }
}