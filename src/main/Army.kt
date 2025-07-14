package main

class Army(
    val troops: MutableList<Warrior> = mutableListOf()
) : Iterable<Warrior> {
    val isAlive: Boolean
        get() = troops.any { it.isAlive }

    fun takeHit(damage: Int) {
        val aliveWarrior = troops.firstOrNull { it.isAlive }
        aliveWarrior?.health = (aliveWarrior?.health ?: 0) - damage
    }

    val champion: Warrior?
        get() = troops.firstOrNull { it.isAlive }

    val secondChampion: Warrior?
        get() = troops.filter { it.isAlive }.drop(1).firstOrNull()

    fun addUnits(warrior: Warrior) {
        troops.add(warrior)
    }

    override fun iterator(): Iterator<Warrior> = troops.iterator()
}

fun Army.addUnits(n: Int, factory: ()->Warrior) {
    repeat(n) { addUnits(factory()) }
}

fun fight(first: Army, second: Army): Boolean {
    val firstIterator = first.iterator()
    if(!firstIterator.hasNext()) return false

    val secondIterator = second.iterator()
    if(!firstIterator.hasNext()) return true

    var firstChampion = firstIterator.next()
    var secondChampion = secondIterator.next()

    while (true){
        val res = fight(firstChampion, secondChampion, first, second)
        if(res) {
            if(!secondIterator.hasNext()) return true
            secondChampion = secondIterator.next()
        } else {
            if(!firstIterator.hasNext()) return false
            firstChampion = firstIterator.next()
        }
    }

    return first.isAlive
}