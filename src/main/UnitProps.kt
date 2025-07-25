package main

object UnitProps {
    object Warrior {
        const val HEALTH = 50
        const val ATTACK = 5
    }
    object Knight {
        const val ATTACK = 7
    }
    object Defender {
        const val HEALTH = 60
        const val ATTACK = 3
        const val DEFENSE = 2
    }
    object Lancer {
        const val HEALTH = 50
        const val ATTACK = 6
        val ATTACK_EFFECTS = listOf(PiercingCollateral())
    }
    object Vampire {
        const val HEALTH = 40
        const val ATTACK = 4
        val ATTACK_EFFECTS = listOf(Vampirism())
    }
    object Rookie {
        const val HEALTH = 50
        const val ATTACK = 1
    }
}