package main


class Defender(
    health: Int = 60,
    attack: Int = 3,
    val defense: Int = 2
) : Warrior(health = health, attack = attack) {
    override fun calculateTakenHit(damage: Int): Int {
        return (damage - this.defense).coerceAtLeast(0)
    }
}