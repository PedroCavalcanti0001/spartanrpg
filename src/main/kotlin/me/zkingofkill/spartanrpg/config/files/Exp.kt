package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.SpartanRPG
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "Exp.yml")
class Exp : ConfigBuilder() {

    @ConfigValue(path = "gainExperienceOptions.breakBlocks")
    var blockList = arrayListOf("1:1","STONE")

    fun init(){
        super.init(SpartanRPG.singleton, this)
    }
}