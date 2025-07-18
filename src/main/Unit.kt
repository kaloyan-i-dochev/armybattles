package main

/**
 * Base interface for all combat units
 */
interface Unit {
    var health: Int
    val attack: Int
    val isAlive: Boolean
        get() = health > 0

    fun takeHit(damage: Int)
    fun calculateTakenHit(damage: Int): Int = damage
    fun getActions(): List<Action>
}
