package main

open class Warrior(
    override var health: Int = 50,
    override val attack: Int = 5,
    var attackEffects: List<Effect> = emptyList()
) : Unit {

    override fun takeHit(damage: Int) {
        this.health -= this.calculateTakenHit(damage)
    }

    override fun calculateTakenHit(damage: Int): Int {
        return damage
    }

    override fun getActions(): List<Action> {
        return if (attackEffects.isEmpty()) {
            listOf(BasicAttack(attack))
        } else {
            listOf(EnhancedAttack(attack, attackEffects))
        }
    }
}

val Warrior.isAlive: Boolean
    get() = health > 0

// Legacy infix function - delegates to new system
fun Warrior.hits(other: Warrior) {
    val fightContext = FightContext(this, other)
    val context = AttackContext(this, other, fightContext)
    val actions = this.getActions()
    for (action in actions) {
        val effects = action.execute(context)
        effects.forEach { it.apply(context) }
    }
}

// Legacy fight functions - delegate to new controller system
fun fight(first: Warrior, second: Warrior): Boolean {
    val fightController = FightController()
    val fightContext = FightContext(first, second)
    return fightController.executeFight(fightContext)
}

fun fight(first: Warrior, second: Warrior, firstArmy: Army?, secondArmy: Army?): Boolean {
    return if (firstArmy != null && secondArmy != null) {
        val battleContext = BattleContext(firstArmy, secondArmy)
        val fightContext = FightContext(first, second, battleContext)
        val fightController = FightController()
        return fightController.executeFight(fightContext)
    } else {
        fight(first, second)
    }
}

fun Warrior.hits(other: Warrior, thisArmy: Army, otherArmy: Army) {
    val battleContext = BattleContext(thisArmy, otherArmy)
    val fightContext = FightContext(this, other, battleContext)
    val context = AttackContext(this, other, fightContext)
    val actions = this.getActions()
    for (action in actions) {
        val effects = action.execute(context)
        effects.forEach { it.apply(context) }
    }
}

// Global fight function for any Unit types
fun fight(first: Unit, second: Unit): Boolean {
    val fightController = FightController()
    val fightContext = FightContext(first, second)
    return fightController.executeFight(fightContext)
}
