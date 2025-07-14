package main

class Vampire(
    health: Int = 40,
    attack: Int = 4,
    attackEffects: List<Effect> = listOf(Vampirism())
) : Warrior(health, attack, attackEffects) {
}