package me.zkingofkill.spartanrpg.config

import me.zkingofkill.spartanrpg.config.files.*

class Config {
    val classes: Classes = Classes()
    val exp: Exp = Exp()
    val messages: Messages = Messages()
    val mysql: Mysql = Mysql()
    val guis:Guis = Guis()
    val levels:Levels = Levels()

    init {
        classes.init()
        exp.init()
        messages.init()
        mysql.init()
        guis.init()
        levels.init()
    }
}