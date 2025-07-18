package main

/**
 * Controller for managing one-on-one duels between units
 */
class FightController {
    fun executeFight(context: FightContext): Boolean {
        val unit1 = context.unit1
        val unit2 = context.unit2

        while (unit1.isAlive && unit2.isAlive) {
            // Unit1 attacks Unit2
            val attackContext1 = AttackContext(unit1, unit2, context)
            executeAttack(attackContext1)
            context.attackHistory.add(attackContext1)

            // Unit2 counter-attacks if still alive
            if (unit2.isAlive) {
                val attackContext2 = AttackContext(unit2, unit1, context)
                executeAttack(attackContext2)
                context.attackHistory.add(attackContext2)
            }
        }

        return unit1.isAlive
    }

    private fun executeAttack(context: AttackContext) {
        val actions = context.attacker.getActions()
        for (action in actions) {
            val effects = action.execute(context)
            effects.forEach { it.apply(context) }
        }
    }
}

/**
 * Controller for managing army-vs-army battles
 */
class BattleController {
    private val fightController = FightController()

    fun executeBattle(context: BattleContext): Boolean {
        val army1Iterator = context.army1.iterator()
        val army2Iterator = context.army2.iterator()

        if (!army1Iterator.hasNext()) return false
        if (!army2Iterator.hasNext()) return true

        var champion1 = army1Iterator.next()
        var champion2 = army2Iterator.next()

        while (true) {
            val fightContext = FightContext(champion1, champion2, context)
            val result = fightController.executeFight(fightContext)
            context.battleHistory.add(fightContext)

            if (result) {
                // Army1 champion won
                if (!army2Iterator.hasNext()) return true
                champion2 = army2Iterator.next()
            } else {
                // Army2 champion won
                if (!army1Iterator.hasNext()) return false
                champion1 = army1Iterator.next()
            }
        }
    }
}
