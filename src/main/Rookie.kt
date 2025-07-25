package main

class Rookie(
    health: Int = UnitProps.Rookie.HEALTH,
    attack: Int = UnitProps.Rookie.ATTACK
) : Warrior(health = health, attack = attack)