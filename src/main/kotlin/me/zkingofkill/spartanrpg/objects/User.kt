package me.zkingofkill.spartanrpg.objects

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.objects.enums.ClassType
import me.zkingofkill.spartanrpg.database.UserDAO
import me.zkingofkill.spartanrpg.objects.classes.Class
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class User(
    val playername: String,
    var naturalEnergy: Double = 0.0,
    var arcaneEnergy: Double = 0.0,
    var exp: Double = 0.0,
    var level: Int = 0,
    var clazzType: ClassType = ClassType.ALCHEMIST
) {
    fun upLevel(levels: Int = 1): Boolean {
        var announce = false
        for (i in 1..levels) {
            if (level + 1 <= maxLevel()) {
                level += 1
                announce = true
                continue
            }
            break
        }
        if (announce) {
            Bukkit.broadcastMessage("$playername upou de nivel")
        }
        return announce
    }

    fun defLevel(level: Int) {
        this.level = level
        exp = 0.0
    }

    fun giveExp(exp: Double): Boolean {
        this.exp += exp
        var levels = 0
        while (this.exp >= requiredExp()) {
            if (level == maxLevel()) {
                Bukkit.getPlayer(playername)?.sendMessage(SpartanRPG.singleton.config.messages.maxLevel)
                return true
            }
            this.exp -= this.requiredExp()
            levels++
        }
        upLevel(levels)
        return false
    }

    fun requiredExp(): Double {
        val c = SpartanRPG.singleton.config.levels
        val index = c.levels().indexOfFirst { it.level == level + 1 }
        val requiredExp = 0.0
        if (index != -1) {
            return c.levels()[index].exp
        }
        return requiredExp
    }

    fun maxLevel(): Int {
        return SpartanRPG.singleton.config.levels.levels().last().level
    }

    var dao = UserDAO(this.playername)

    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        var users = arrayListOf<User>()

        fun get(playername: String): User? {
            val search = users.filter { it.playername == playername }
            if (search.isNullOrEmpty()) {
                val user = UserDAO(playername).get()
                if (user != null) {
                    users.add(user)
                }
                return user
            }
            return search.first()
        }

        fun saveAll() {
            users.forEach { it.dao.upSert() }
        }

    }
}


