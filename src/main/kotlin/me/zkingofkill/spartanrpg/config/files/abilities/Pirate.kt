package me.zkingofkill.spartanrpg.config.files.abilities

import me.zkingofkill.spartanrpg.SpartanRPG
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "abilities/pirate.yml")
class Pirate : ConfigBuilder() {

    @ConfigValue
    var name = "pirata"

    @ConfigValue
    var leveMax = 2

    @ConfigValue(path = "levels.1.percentage")
    private var levelOnePercentage = 10.0

    @ConfigValue(path = "menuItem")
    var menuItem = "STONE:0"

    @ConfigValue(path = "description", translateAlternativeColors = true)
    var description = arrayListOf("&bHabilidade legal, obrigado","&ahaha {chance}")

    @ConfigValue(path = "levels.1.requiredPoints")
    private var levelOneRequiredPoints = 300

    @ConfigValue(path = "levels.2.percentage")
    private var levelTwoPercentage = 10.0

    @ConfigValue(path = "levels.2.requiredPoints")
    private var levelTwoRequiredPoints = 600

    fun init() {
        super.init(SpartanRPG.singleton, this)
    }
}