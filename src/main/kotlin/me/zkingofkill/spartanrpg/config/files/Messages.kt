package me.zkingofkill.spartanrpg.config.files

import me.zkingofkill.spartanrpg.SpartanRPG
import me.zkingofkill.spartanrpg.objects.User
import spartanconfigapi.ConfigBuilder
import spartanconfigapi.annotations.Config
import spartanconfigapi.annotations.ConfigValue

@Config(path = "messages.yml")
class Messages : ConfigBuilder() {


    @ConfigValue(translateAlternativeColors = true)
    var prefix = "&5[&dSpartanRPG&5]"

    @ConfigValue(translateAlternativeColors = true)
    var noPermission = "{prefix} &aVocê não tem permissão.".replace("{prefix}", prefix)

    var maxLevel = "{prefix} &cVocê chegou no nivel maximo!".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var requireDoubleNumber =
        "{prefix} &cO valor digitado precisa ser um numero com ponto flutuante.".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var requireIntegerNumber =
        "{prefix} &cO valor digitado precisa ser um numero inteiro.".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var changedLevel =
        "{prefix} &aNivel alterado com sucesso!.".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var playerNotFound = "{prefix} &cJogador não encontrado!".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var maxLevelExceeded = "{prefix} &cO nivel precisa ser menor que o nivel maximo.".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var argGiveExp = "&7Use: &2/rpg darxp <jogador> <quantidade>"

    @ConfigValue(translateAlternativeColors = true)
    var argSetLevel = "&7Use: &2/rpg setnivel <jogador> <nivel>"

    @ConfigValue(translateAlternativeColors = true)
    var successfullyGivenExperience = "{prefix} &aExperiencia dada com sucesso!".replace("{prefix}", prefix)

    @ConfigValue(translateAlternativeColors = true)
    var args = arrayListOf(
        "&aComandos: ",
        "{giveexp}".replace("{giveexp}", argGiveExp),
        "{setlevel}".replace("{setlevel}", argSetLevel)
    )

    fun init() {
        super.init(SpartanRPG.singleton, this)
    }
}