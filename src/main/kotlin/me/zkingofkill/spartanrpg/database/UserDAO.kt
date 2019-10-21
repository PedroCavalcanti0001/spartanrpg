package me.zkingofkill.spartanrpg.database

import com.google.gson.Gson
import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.models.User
import java.sql.SQLException


class UserDAO(var playername: String) : ConnectionFactory() {

    fun upSert(): Boolean {
        try {
            val con = open()
            val user = User.get(playername)
            val insert =
                con!!.prepareStatement(
                    "INSERT INTO ${SpartanRPG.singleton.config.mysql.mysqlTable}" +
                            "(player, user) VALUES (?,?) ON DUPLICATE KEY UPDATE player = ?, user = ?;"
                )
            val gson = Gson()
            insert.setString(1, playername)
            insert.setString(2, gson.toJson(user))
            insert.setString(3, playername)
            insert.setString(4, gson.toJson(user))
            insert.executeUpdate()
            con.close()
            return true

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return false
    }

    fun remove(): Boolean {
        try {
            val con = open()!!
            val insert = con.prepareStatement("DELETE FROM ${SpartanRPG.singleton.config.mysql.mysqlTable} WHERE player = ?;")
            insert.setString(1, playername)
            insert.execute()
            con.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return false
    }

    fun get(): User? {
        val connection = open()
        val ps =
            connection!!.prepareStatement("SELECT * FROM ${SpartanRPG.singleton.config.mysql.mysqlTable} WHERE player = ?")
        ps?.setString(1, playername)
        val rs = ps.executeQuery()
        return if(rs.next())Gson().fromJson(rs?.getString("user"), User::class.java) else null
    }
}