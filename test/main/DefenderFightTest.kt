package main

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DefenderFightTest {

    @Test
    fun `carl vs jim`() {
        val carl = Warrior()
        val jim = Knight()
        assertFalse(fight(carl, jim))
    }

    @Test
    fun `ramon vs slevin`() {
        val ramon = Knight()
        val slevin = Warrior()
        assertTrue(fight(ramon, slevin))
    }

    @Test
    fun `bob vs mars, bob is alive`() {
        val bob = Warrior()
        val mars = Warrior()
        fight(bob, mars)
        assertTrue(bob.isAlive)
    }

    @Test
    fun `zeus vs godkiller, zeus is alive`() {
        val zeus = Knight()
        val godkiller = Warrior()
        fight(zeus, godkiller)
        assertTrue(zeus.isAlive)
    }

    @Test
    fun `husband vs wife, wife is not alive`() {
        val husband = Warrior()
        val wife = Warrior()
        fight(husband, wife)
        assertFalse(wife.isAlive)
    }

    @Test
    fun `dragon vs knight, knight is alive`() {
        val dragon = Warrior()
        val knight = Knight()
        fight(dragon, knight)
        assertTrue(knight.isAlive)
    }

    @Test
    fun `unit_1 vs unit_2, then unit_2 vs unit_3, unit_2 loses`() {
        val unit1 = Warrior()
        val unit2 = Knight()
        val unit3 = Warrior()
        fight(unit1, unit2)
        assertFalse(fight(unit2, unit3))
    }

    @Test
    fun `defender vs rookie, defender health remains 60`() {
        val unit1 = Defender()
        val unit2 = Rookie()
        fight(unit1, unit2)
        assertEquals(60, unit1.health)
    }

    @Test
    fun `defender vs rookie, then defender vs warrior, defender wins`() {
        val unit1 = Defender()
        val unit2 = Rookie()
        val unit3 = Warrior()
        fight(unit1, unit2)
        assertTrue(fight(unit1, unit3))
    }

    @Test
    fun `army1 1 warrior vs army2 2 warriors`() {
        val army1 = Army().apply { addUnits(1) { Warrior() } }
        val army2 = Army().apply { addUnits(2) { Warrior() } }
        assertFalse(fight(army1, army2))
    }

    @Test
    fun `army1 2 warriors vs army2 3 warriors`() {
        val army1 = Army().apply { addUnits(2) { Warrior() } }
        val army2 = Army().apply { addUnits(3) { Warrior() } }
        assertFalse(fight(army1, army2))
    }

    @Test
    fun `army1 5 warriors vs army2 7 warriors`() {
        val army1 = Army().apply { addUnits(5) { Warrior() } }
        val army2 = Army().apply { addUnits(7) { Warrior() } }
        assertFalse(fight(army1, army2))
    }

    @Test
    fun `army1 20 warriors vs army2 21 warriors`() {
        val army1 = Army().apply { addUnits(20) { Warrior() } }
        val army2 = Army().apply { addUnits(21) { Warrior() } }
        assertTrue(fight(army1, army2))
    }

    @Test
    fun `army1 10 warriors vs army2 11 warriors`() {
        val army1 = Army().apply { addUnits(10) { Warrior() } }
        val army2 = Army().apply { addUnits(11) { Warrior() } }
        assertTrue(fight(army1, army2))
    }

    @Test
    fun `army1 11 warriors vs army2 7 warriors`() {
        val army1 = Army().apply { addUnits(11) { Warrior() } }
        val army2 = Army().apply { addUnits(7) { Warrior() } }
        assertTrue(fight(army1, army2))
    }

    @Test
    fun `army1 5 warriors, 9 defenders vs army2 4 warriors`() {
        val army1 = Army().apply {
            addUnits(5) { Warrior() }
            addUnits(4) { Defender() }
            addUnits(5) { Defender() }
        }
        val army2 = Army().apply { addUnits(4) { Warrior() } }
        assertTrue(fight(army1, army2))
    }

    @Test
    fun `army1 5 defenders, 20 warriors, 4 defenders vs army2 21 warriors`() {
        val army1 = Army().apply {
            addUnits(5) { Defender() }
            addUnits(20) { Warrior() }
            addUnits(4) { Defender() }
        }
        val army2 = Army().apply { addUnits(21) { Warrior() } }
        assertTrue(fight(army1, army2))
    }

    @Test
    fun `army1 10 warriors, 5 defenders, 10 defenders vs army2 5 warriors`() {
        val army1 = Army().apply {
            addUnits(10) { Warrior() }
            addUnits(5) { Defender() }
            addUnits(10) { Defender() }
        }
        val army2 = Army().apply { addUnits(5) { Warrior() } }
        assertTrue(fight(army1, army2))
    }

    @Test
    fun `army1 2 defenders, 1 warrior, 1 defender vs army2 5 warriors`() {
        val army1 = Army().apply {
            addUnits(2) { Defender() }
            addUnits(1) { Warrior() }
            addUnits(1) { Defender() }
        }
        val army2 = Army().apply { addUnits(5) { Warrior() } }
        assertFalse(fight(army1, army2))
    }
}