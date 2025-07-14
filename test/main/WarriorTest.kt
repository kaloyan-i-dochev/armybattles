package main

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WarriorTest {
    @Test
    fun `chuck fights bruce`(){
        val chuck = Warrior()
        val bruce = Warrior()

        val res = fight(chuck, bruce)

        assertTrue(res, "Chuck should win")

        assertTrue(chuck.isAlive, "Chuck should be alive")
        assertFalse(bruce.isAlive, "Bruce should be dead")
    }


    @Test
    fun `carl fights dave`(){
        val carl = Knight()
        val dave = Warrior()

        assertFalse(fight(dave, carl), "Carl should win")

        assertTrue(carl.isAlive, "Carl should be alive")
        assertFalse(dave.isAlive, "Dave should be dead")
    }

    @Test
    @DisplayName("1. Fight")
    fun `Warrior loses when fights against Knight`() {
        // given
        val carl = Warrior()
        val jon = Knight()
        // when
        val res = fight(carl, jon)
        // then
        assertEquals(false, res) {
            "Warrior should lose against Knight"
        }
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
        val unit_1 = Warrior()
        val unit_2 = Knight()
        val unit_3 = Warrior()
        fight(unit_1, unit_2)
        assertFalse(fight(unit_2, unit_3))
    }
}