package main

class Lancer (
    health: Int = UnitProps.Lancer.HEALTH,
    attack: Int = UnitProps.Lancer.ATTACK,
    attackEffects: List<Effect> = UnitProps.Lancer.ATTACK_EFFECTS
) : Warrior(health, attack, attackEffects) {}