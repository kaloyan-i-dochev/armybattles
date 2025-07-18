package main

class Army(
    val troops: MutableList<Unit> = mutableListOf()
) : Iterable<Unit> {
    val isAlive: Boolean
        get() = troops.any { it.isAlive }

    fun takeHit(damage: Int) {
        val aliveUnit = troops.firstOrNull { it.isAlive }
        aliveUnit?.takeHit(damage)
    }

    val champion: Unit?
        get() = troops.firstOrNull { it.isAlive }

    val secondChampion: Unit?
        get() = troops.filter { it.isAlive }.drop(1).firstOrNull()

    fun addUnits(unit: Unit) {
        troops.add(unit)
    }

    override fun iterator(): Iterator<Unit> = troops.iterator()
}

fun Army.addUnits(n: Int, factory: () -> Unit) {
    repeat(n) { addUnits(factory()) }
}


fun fight(army1: Army, army2: Army): Boolean {
    val battleController = BattleController()
    val battleContext = BattleContext(army1, army2)
    return battleController.executeBattle(battleContext)
}