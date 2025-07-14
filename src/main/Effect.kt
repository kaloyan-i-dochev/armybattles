package main

open class Effect

class Vampirism(
    val ratio: Double = 0.5
) : Effect() {
    fun applyEffect(attacker: Warrior, defender: Warrior) {
        if (attacker is Vampire) {
            val damage = attacker.attack
            val damageTaken = defender.calculateTakenHit(damage)
            attacker.health += (damageTaken * ratio).toInt()
        }
    }
}