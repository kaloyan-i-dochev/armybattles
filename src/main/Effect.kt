package main

open class Effect () {
    open fun applyEffect(attacker: Warrior, defender: Warrior) {
        // Default implementation does nothing
    }
    open fun applyEffect(attacker: Warrior, attackingArmy: Army, defender: Warrior, defendingArmy: Army) {
        // Default implementation does nothing
    }
}

class Vampirism(
    val ratio: Double = 0.5
) : Effect() {
    override fun applyEffect(attacker: Warrior, defender: Warrior) {
        if (attacker is Vampire) {
            val damage = attacker.attack
            val damageTaken = defender.calculateTakenHit(damage)
            attacker.health += (damageTaken * ratio).toInt()
        }
    }

    override fun applyEffect(attacker: Warrior, attackingArmy: Army, defender: Warrior, defendingArmy: Army) {
        this.applyEffect(attacker, defender)
    }
}

class LanceStrike(
    val ratio: Double = 0.5
) : Effect() {
    override fun applyEffect(attacker: Warrior, defender: Warrior) {

    }

    override fun applyEffect(attacker: Warrior, attackingArmy: Army, defender: Warrior, defendingArmy: Army) {
        if(attacker is Lancer) {
            val damage = (attacker.attack * ratio).toInt()
            val secondDefender = defendingArmy.secondChampion;
            secondDefender?.takeHit(damage);
        }
    }
}