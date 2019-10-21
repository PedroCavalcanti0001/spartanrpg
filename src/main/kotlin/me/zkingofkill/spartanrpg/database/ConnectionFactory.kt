package me.zkingofkill.spartanrpg.database

import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.models.User
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import java.sql.Connection
import java.sql.DriverManager
import java.util.logging.Level


open class ConnectionFactory {

    fun open(): Connection? {
        try {
            val password = SpartanRPG.singleton.config.mysql.mysqlPassword
            val user =  SpartanRPG.singleton.config.mysql.mysqlUsername
            val host =  SpartanRPG.singleton.config.mysql.mysqlHost
            val port =  SpartanRPG.singleton.config.mysql.mysqlPort
            val database =  SpartanRPG.singleton.config.mysql.mysqlDatabase
            val type = "jdbc:mysql://"
            val url = "$type$host:$port/$database?characterEncoding=utf8&useConfigs=maxPerformance"
            return DriverManager.getConnection(url, user, password)
        } catch (e: Exception) {
            print("Ocorreu um erro no mysql:")
            e.printStackTrace()
        } finally {
            println("Conexão efetuada com sucesso!")
        }

        return null
    }

    companion object {
        fun init() {
            val connection = ConnectionFactory().open()
            val ps = connection?.prepareStatement(
                "CREATE TABLE IF NOT EXISTS ${SpartanRPG.singleton.config.mysql.mysqlTable}" +
                        "(`player` VARCHAR(35), `user` LONGTEXT NOT NULL, PRIMARY KEY(`player`));"
            )
            ps?.execute()

            object : BukkitRunnable() {
                override fun run() {
                    try {
                        ArrayList(User.users).forEach {
                            it.dao.upSert()
                            if (Bukkit.getPlayer(it.playername) == null) {
                                it.dao.remove()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        SpartanRPG.singleton.server.logger.log(Level.WARNING, "Usuários salvos com sucesso.")
                    }
                }
            }.runTaskTimerAsynchronously(SpartanRPG.singleton, 5 * 20 * 60, 5 * 20 * 60)
        }
    }


}