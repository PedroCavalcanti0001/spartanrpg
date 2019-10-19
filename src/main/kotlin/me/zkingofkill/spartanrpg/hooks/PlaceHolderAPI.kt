package me.zkingofkill.spartanrpg.hooks

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.zkingofkill.spartanrpg.objects.User
import org.bukkit.entity.Player
import kotlin.math.roundToInt


class PlaceHolderAPI : PlaceholderExpansion() {

    override fun getVersion(): String {
        return "1.0"
    }

    override fun getAuthor(): String {
        return "zKingOfKill"
    }

    override fun getIdentifier(): String {
        return "spartanrpg"
    }

    override fun canRegister(): Boolean {
        return true
    }

    override fun onPlaceholderRequest(p: Player, params: String): String {
        val user = User.get(p.name)
        if (user == null) {
            return ""
        } else {
            if (params == "class") {
                return user.clazzType.name
            }
            if (params == "expToNext") {
                return user.requiredExp().roundToInt().toString()
            }
            if (params == "exp") {
                return user.exp.roundToInt().toString()
            }
            if (params == "level") {
                return user.level.toString()
            }
        }
        return ""
    }
}