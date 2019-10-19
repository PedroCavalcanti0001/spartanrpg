package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.SpartanRPG
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "Mysql.yml")
class Mysql : ConfigBuilder() {

    @ConfigValue(path ="mysql.host")
    var mysqlHost = "localhost"

    @ConfigValue(path ="mysql.port")
    var mysqlPort = 3306

    @ConfigValue(path ="mysql.password")
    var mysqlPassword = "123"

    @ConfigValue(path ="mysql.Username")
    var mysqlUsername = "root"

    @ConfigValue(path ="mysql.table")
    var mysqlTable = "SpartanRPG"

    @ConfigValue(path ="mysql.database")
    var mysqlDatabase = "database"

    fun init(){
        super.init(SpartanRPG.singleton, this)
    }
}