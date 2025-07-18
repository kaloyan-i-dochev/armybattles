package main

/**
 * Sealed class hierarchy for post-attack effects
 */
sealed class Effect {
    abstract fun apply(context: AttackContext)
}

/**
 * Basic damage effect
 */
class Damage(val amount: Int) : Effect() {
    override fun apply(context: AttackContext) {
        context.defender.takeHit(amount)
    }
}

/**
 * Vampirism effect - attacker steals life from damage dealt
 */
class Vampirism(val ratio: Double = 0.5) : Effect() {
    override fun apply(context: AttackContext) {
        val damageTaken = context.defender.calculateTakenHit(context.attacker.attack)
        val lifeStolen = (damageTaken * ratio).toInt()
        context.attacker.health += lifeStolen
    }
}

/**
 * Piercing collateral damage effect - hits second target in line
 */
class PiercingCollateral(val ratio: Double = 0.5) : Effect() {
    override fun apply(context: AttackContext) {
        val piercingDamage = (context.attacker.attack * ratio).toInt()
        val secondaryTarget = getSecondaryTarget(context)
        secondaryTarget?.takeHit(piercingDamage)
    }

    private fun getSecondaryTarget(context: AttackContext): Unit? {
        return context.fightContext?.battleContext?.let { battle ->
            when (context.attacker) {
                battle.army1.champion -> battle.army2.secondChampion
                battle.army2.champion -> battle.army1.secondChampion
                else -> null
            }
        }
    }
}

/**
 * Lance strike effect - specific piercing attack for Lancers
 * @deprecated Use PiercingCollateral instead
 */
@Deprecated("Use PiercingCollateral instead", ReplaceWith("PiercingCollateral(ratio)"))
class LanceStrike(val ratio: Double = 0.5) : Effect() {
    override fun apply(context: AttackContext) {
        // Delegate to PiercingCollateral for compatibility
        PiercingCollateral(ratio).apply(context)
    }
}