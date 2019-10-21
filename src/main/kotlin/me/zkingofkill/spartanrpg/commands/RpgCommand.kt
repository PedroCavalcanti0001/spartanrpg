package me.zkingofkill.spartanrpg.commands

import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.models.User
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import kotlin.math.absoluteValue

class RpgCommand : CommandExecutor {
    val cfs = SpartanRPG.singleton.config
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("spartanrpg.admin")) {
            if (args.isEmpty()) {
                cfs.messages.send(sender, cfs.messages.args)
                return false
            } else {
                if (args[0].equals("darexp", true)) {
                    if (args.size >= 3) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            return if (args[2].matches("[0-9]+.[0-9]+".toRegex())) {
                                val user = User.get(args[1]) as User
                                user.giveExp(args[2].toDouble())
                                sender.sendMessage(cfs.messages.successfullyGivenExperience)
                                true
                            } else {
                                sender.sendMessage(cfs.messages.requireDoubleNumber)
                                false
                            }
                        } else {
                            sender.sendMessage(cfs.messages.playerNotFound)
                        }
                    } else {
                        sender.sendMessage(cfs.messages.argGiveExp)
                    }
                }
                if (args[0].equals("setnivel", true)) {
                    if (args.size >= 3) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            return if (args[2].matches("[+-]?[0-9][0-9]*".toRegex())) {
                                val number = args[2].toInt().absoluteValue
                                val user = User.get(args[1]) as User
                                if (number <= user.maxLevel()) {
                                    user.level = number
                                    sender.sendMessage(cfs.messages.changedLevel)
                                    true
                                } else {
                                    sender.sendMessage(cfs.messages.maxLevelExceeded)
                                    false
                                }

                            } else {
                                sender.sendMessage(cfs.messages.requireIntegerNumber)
                                false
                            }
                        } else {
                            sender.sendMessage(cfs.messages.playerNotFound)
                        }
                    } else {
                        sender.sendMessage(cfs.messages.argSetLevel)
                    }
                }
            }
        } else {
            sender.sendMessage(cfs.messages.noPermission)
            return false
        }
        return false
    }
}