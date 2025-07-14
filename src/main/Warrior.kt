package main

    open class Warrior(
        var health: Int = 50,
        val attack: Int = 5,
        var attackEffects: List<Effect> = emptyList()
    ) {
        open fun takeHit(damage: Int) {
            this.health -= this.calculateTakenHit(damage)
        }

        open fun calculateTakenHit(damage: Int): Int {
            return damage
        }
    }

    val Warrior.isAlive: Boolean
        get() = health > 0

    infix fun Warrior.hits(other: Warrior) {
        other.takeHit(this.attack)
        this.attackEffects.forEach { effect ->
            if (effect is Vampirism) {
                effect.applyEffect(this, other)
            }
        }
    }

    fun fight(first: Warrior, second: Warrior) : Boolean {
        while (first.isAlive && second.isAlive) {
            first hits second
            if(second.isAlive) {
                second hits first
            }
        }
        return first.isAlive
    }