package main

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ArmyFightTest {

    @Test
    fun `carl fights jim`() {
        val carl = Warrior()
        val jim = Knight()
        assertFalse(fight(carl, jim))
    }

    @Test
    fun `ramon fights slevin`() {
        val ramon = Knight()
        val slevin = Warrior()
        assertTrue(fight(ramon, slevin))
    }

    @Test
    fun `bob fights mars and bob is alive`() {
        val bob = Warrior()
        val mars = Warrior()
        fight(bob, mars)
        assertTrue(bob.isAlive)
    }

    @Test
    fun `zeus fights godkiller and zeus is alive`() {
        val zeus = Knight()
        val godkiller = Warrior()
        fight(zeus, godkiller)
        assertTrue(zeus.isAlive)
    }

    @Test
    fun `husband fights wife and wife is not alive`() {
        val husband = Warrior()
        val wife = Warrior()
        fight(husband, wife)
        assertFalse(wife.isAlive)
    }

    @Test
    fun `dragon fights knight and knight is alive`() {
        val dragon = Warrior()
        val knight = Knight()
        fight(dragon, knight)
        assertTrue(knight.isAlive)
    }

    @Test
    fun `unit_1 fights unit_2 then unit_2 fights unit_3 and unit_2 loses`() {
        val unit1 = Warrior()
        val unit2 = Knight()
        val unit3 = Warrior()
        fight(unit1, unit2)
        assertFalse(fight(unit2, unit3))
    }

    // This is where I have a unique implementation that is not from the lectures
    @ParameterizedTest(name = "{0} {1}s vs {2} {3}s should result in {4}")
    @MethodSource("armyBattleTestData")
    fun `army battles`(
        army1Count: Int,
        army1Type: String,
        army2Count: Int,
        army2Type: String,
        expectedResult: Boolean
    ) {
        val army1 = Army().apply {
            addUnits(army1Count) {
                when (army1Type) {
                    "Knight" -> Knight()
                    else -> Warrior()
                }
            }
        }
        val army2 = Army().apply {
            addUnits(army2Count) {
                when (army2Type) {
                    "Knight" -> Knight()
                    else -> Warrior()
                }
            }
        }

        assertEquals(expectedResult, fight(army1, army2))
    }

    companion object {
        @JvmStatic
        fun armyBattleTestData(): Stream<Arguments> = Stream.of(
            Arguments.of(1, "Warrior", 2, "Warrior", false),
            Arguments.of(2, "Warrior", 3, "Warrior", false),
            Arguments.of(5, "Warrior", 7, "Warrior", false),
            Arguments.of(20, "Warrior", 21, "Warrior", true),
            Arguments.of(10, "Warrior", 11, "Warrior", true),
            Arguments.of(11, "Warrior", 7, "Warrior", true),
            Arguments.of(5, "Knight", 7, "Warrior", true),
            Arguments.of(5, "Knight", 8, "Warrior", false)
        )
    }

    // Adding a 3 army battle as requested during the lecture
    @Test
    fun `army1 loses to army2 then army2 loses to army3`() {
        val army1 = Army().apply {
            addUnits(10) { Warrior() }
        }

        val army2 = Army().apply {
            addUnits(15) { Knight() }
        }

        val army3 = Army().apply {
            addUnits(10) { Knight() }
        }

        assertFalse(
            fight(army1, army2),
            "Army₂ (Knight) should beat Army₁ (Warrior)"
        )

        assertFalse(
            fight(army2, army3),
            "Army₃ (2 Knights) should beat Army₂ (1 Knight)"
        )
    }
}