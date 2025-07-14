package main

open class Warrior(
    var health: Int = 50,
    val attack: Int = 5
)

val Warrior.isAlive: Boolean
    get() = health > 0

infix fun Warrior.hits(other: Warrior) {
    other.health -= this.attack
//    this.health -= other.attack
}

class Knight(
    attack: Int = 7
) : Warrior(attack = attack)

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

    fun addUnits(warrior: Warrior) {
        troops.add(warrior)
    }

    override fun iterator(): Iterator<Warrior> = troops.iterator()
}

fun Army.addUnits(n: Int, factory: ()->Warrior) {
    repeat(n) { addUnits(factory()) }
}

fun fight(first: Warrior, second: Warrior) : Boolean {
    while (first.isAlive && second.isAlive) {
        first hits second
        if(second.isAlive) {
            second hits first
        }
    }

    return first.isAlive
}

// I wrote this function during the lecture
fun fight(first: Army, second: Army): Boolean {
    val firstIterator = first.iterator()
    if(!firstIterator.hasNext()) return false

    val secondIterator = second.iterator()
    if(!firstIterator.hasNext()) return true

    var firstChampion = firstIterator.next()
    var secondChampion = secondIterator.next()

    while (true){
        val res = fight(firstChampion, secondChampion)
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

fun main() {
    val myArmy = Army()
    myArmy.addUnits(3) { Knight() }

    val enemyArmy = Army()
    enemyArmy.addUnits(3) { Warrior() }

    val army3 = Army()
    army3.addUnits(20) { Warrior() }
    army3.addUnits(5) { Knight() }

    val army4 = Army()
    army4.addUnits(30) { Warrior() }

    check(fight(myArmy, enemyArmy) == true)
    check(fight(army3, army4) == false)
    println("OK")
}