package main

class Vampire(
    health: Int = UnitProps.Vampire.HEALTH,
    attack: Int = UnitProps.Vampire.ATTACK,
    attackEffects: List<Effect> = UnitProps.Vampire.ATTACK_EFFECTS
) : Warrior(health, attack, attackEffects) {
}