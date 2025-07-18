package main

class Lancer (
    health: Int = 50,
    attack: Int = 6,
    attackEffects: List<Effect> = listOf(PiercingCollateral())
) : Warrior(health, attack, attackEffects) {}