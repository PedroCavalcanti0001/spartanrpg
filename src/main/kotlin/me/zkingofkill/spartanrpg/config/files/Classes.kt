package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.SpartanRPG
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "Classes.yml")
class Classes : ConfigBuilder() {


    @ConfigValue(path = "classes.alchemist.abilities.passive.name")
    var alchemistPassiveName = "Alquimia"

    @ConfigValue(path = "classes.alchemist.abilities.passive.arcane-cost")
    var alchemistPassiveAcaneCost = 10.0

    @ConfigValue(path = "classes.alchemist.abilities.passive.natural-cost")
    var alchemistPassiveNaturalCost = 0.0

    @ConfigValue(path = "classes.alchemist.abilities.passive.chance")
    var alchemistPassiveChance = 10.0

    @ConfigValue(path = "classes.archer.abilities.passive.name")
    var archerPassiveName = "Alquimia"

    @ConfigValue(path = "classes.archer.abilities.passive.arcane-cost")
    var archerPassiveAcaneCost = 10.0

    @ConfigValue(path = "classes.archer.abilities.passive.natural-cost")
    var archerPassiveNaturalCost = 0.0

    @ConfigValue(path = "classes.archer.abilities.passive.chance")
    var archerPassiveChance = 10.0



    @ConfigValue(path = "classes.necromancer.abilities.passive.name")
    var necromancerPassiveName = "Alquimia"

    @ConfigValue(path = "classes.necromancer.abilities.passive.arcane-cost")
    var necromancerPassiveAcaneCost = 10.0

    @ConfigValue(path = "classes.necromancer.abilities.passive.natural-cost")
    var necromancerPassiveNaturalCost = 0.0

    @ConfigValue(path = "classes.necromancer.abilities.passive.chance")
    var necromancerPassiveChance = 10.0



    @ConfigValue(path = "classes.paladin.abilities.passive.name")
    var paladinPassiveName = "Alquimia"

    @ConfigValue(path = "classes.paladin.abilities.passive.arcane-cost")
    var paladinPassiveAcaneCost = 10.0

    @ConfigValue(path = "classes.paladin.abilities.passive.natural-cost")
    var paladinPassiveNaturalCost = 0.0

    @ConfigValue(path = "classes.paladin.abilities.passive.chance")
    var paladinPassiveChance = 10.0



    @ConfigValue(path = "classes.knight.abilities.passive.name")
    var knightPassiveName = "Alquimia"

    @ConfigValue(path = "classes.knight.abilities.passive.arcane-cost")
    var knightPassiveAcaneCost = 10.0

    @ConfigValue(path = "classes.knight.abilities.passive.natural-cost")
    var knightPassiveNaturalCost = 0.0

    @ConfigValue(path = "classes.knight.abilities.passive.chance")
    var knightPassiveChance = 10.0

    fun init(){
        super.init(SpartanRPG.singleton, this)
    }
}