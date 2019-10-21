package me.zkingofkill.spartanrpg.config

import me.zkingofkill.spartanrpg.config.files.*
import me.zkingofkill.spartanrpg.config.files.abilities.Pirate

class Config {
    val exp: Exp = Exp()
    val messages: Messages = Messages()
    val mysql: Mysql = Mysql()
    val guis:Guis = Guis()
    val levels:Levels = Levels()
    val pirate:Pirate = Pirate()
    val generalConfig = GeneralConfig()

    init {
        generalConfig.init()
        pirate.init()
        exp.init()
        messages.init()
        mysql.init()
        guis.init()
        levels.init()
    }
}