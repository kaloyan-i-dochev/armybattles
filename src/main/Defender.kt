package main


class Defender(
    health: Int = UnitProps.Defender.HEALTH,
    attack: Int = UnitProps.Defender.ATTACK,
    val defense: Int = UnitProps.Defender.DEFENSE
) : Warrior(health = health, attack = attack) {
    override fun calculateTakenHit(damage: Int): Int {
        return (damage - this.defense).coerceAtLeast(0)
    }
}