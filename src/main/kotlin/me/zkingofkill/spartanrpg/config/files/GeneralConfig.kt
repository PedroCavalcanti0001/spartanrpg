package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.SpartanRPG
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "general-config.yml")
class GeneralConfig : ConfigBuilder() {

    @ConfigValue(path = "defaultNumberOfConcurrentSkills")
    var defaultNumberOfConcurrentSkills = 3

    fun init(){
        super.init(SpartanRPG.singleton, this)
    }
}