package main

/**
 * Sealed class hierarchy for combat actions
 */
sealed class Action {
    abstract fun execute(context: AttackContext): List<Effect>
}

/**
 * Basic attack action that all units can perform
 */
class BasicAttack(val damage: Int) : Action() {
    override fun execute(context: AttackContext): List<Effect> {
        return listOf(Damage(damage))
    }
}

/**
 * Enhanced attack with additional effects
 */
class EnhancedAttack(val damage: Int, val additionalEffects: List<Effect>) : Action() {
    override fun execute(context: AttackContext): List<Effect> {
        return listOf(Damage(damage)) + additionalEffects
    }
}