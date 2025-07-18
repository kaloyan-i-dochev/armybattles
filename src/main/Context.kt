package main

/**
 * Context for a single attack between two units
 */
data class AttackContext(
    val attacker: Unit,
    val defender: Unit,
    val fightContext: FightContext? = null
) {
    val attackingArmy: Army?
        get() = fightContext?.battleContext?.let { battle ->
            when (attacker) {
                battle.army1.champion -> battle.army1
                battle.army2.champion -> battle.army2
                else -> null
            }
        }

    val defendingArmy: Army?
        get() = fightContext?.battleContext?.let { battle ->
            when (defender) {
                battle.army1.champion -> battle.army1
                battle.army2.champion -> battle.army2
                else -> null
            }
        }
}

/**
 * Context for a complete fight between two units
 */
data class FightContext(
    val unit1: Unit,
    val unit2: Unit,
    val battleContext: BattleContext? = null,
    val attackHistory: MutableList<AttackContext> = mutableListOf()
)

/**
 * Context for a battle between two armies
 */
data class BattleContext(
    val army1: Army,
    val army2: Army,
    val currentFight: FightContext? = null,
    val battleHistory: MutableList<FightContext> = mutableListOf()
)
