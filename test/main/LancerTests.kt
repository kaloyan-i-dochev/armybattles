package main

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LancerTests {

    @Test
    @DisplayName("1. Fight")
    fun `Warrior loses when fights against Knight`() {
        val carl = Warrior()
        val jim = Knight()
        assertFalse(fight(carl, jim))
    }

    @Test
    @DisplayName("2. Fight")
    fun `Knight wins against Warrior`() {
        val ramon = Knight()
        val slevin = Warrior()
        assertTrue(fight(ramon, slevin))
    }

    @Test
    @DisplayName("3. Fight")
    fun `Bob is alive after fighting Mars`() {
        val bob = Warrior()
        val mars = Warrior()
        fight(bob, mars)
        assertTrue(bob.isAlive)
    }

    @Test
    @DisplayName("4. Fight")
    fun `Zeus is alive after fighting Godkiller`() {
        val zeus = Knight()
        val godkiller = Warrior()
        fight(zeus, godkiller)
        assertTrue(zeus.isAlive)
    }

    @Test
    @DisplayName("5. Fight")
    fun `Wife is not alive after fighting Husband`() {
        val husband = Warrior()
        val wife = Warrior()
        fight(husband, wife)
        assertFalse(wife.isAlive)
    }

    @Test
    @DisplayName("6. Fight")
    fun `Knight is alive after fighting Dragon`() {
        val dragon = Warrior()
        val knight = Knight()
        fight(dragon, knight)
        assertTrue(knight.isAlive)
    }

    @Test
    @DisplayName("7. Fight")
    fun `Unit2 loses against Unit3 after fighting Unit1`() {
        val unit1 = Warrior()
        val unit2 = Knight()
        val unit3 = Warrior()
        fight(unit1, unit2)
        assertFalse(fight(unit2, unit3))
    }

    @Test
    @DisplayName("8. Fight")
    fun `Defender has 60 health after fighting Rookie`() {
        val unit1 = Defender()
        val unit2 = Rookie()
        fight(unit1, unit2)
        assertEquals(60, unit1.health)
    }

    @Test
    @DisplayName("9. Fight")
    fun `Defender wins against Warrior after fighting Rookie`() {
        val unit1 = Defender()
        val unit2 = Rookie()
        val unit3 = Warrior()
        fight(unit1, unit2)
        assertTrue(fight(unit1, unit3))
    }

    @Test
    @DisplayName("1. Battle")
    fun `Army with 1 Warrior loses to army with 2 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(1) { Warrior() }
        army2.addUnits(2) { Warrior() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("2. Battle")
    fun `Army with 2 Warriors loses to army with 3 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(2) { Warrior() }
        army2.addUnits(3) { Warrior() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("3. Battle")
    fun `Army with 5 Warriors loses to army with 7 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5) { Warrior() }
        army2.addUnits(7) { Warrior() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("4. Battle")
    fun `Army with 20 Warriors wins against army with 21 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(20) { Warrior() }
        army2.addUnits(21) { Warrior() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("5. Battle")
    fun `Army with 10 Warriors wins against army with 11 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(10) { Warrior() }
        army2.addUnits(11) { Warrior() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("6. Battle")
    fun `Army with 11 Warriors wins against army with 7 Warriors`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(11) { Warrior() }
        army2.addUnits(7) { Warrior() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("7. Battle")
    fun `Mixed army with Warriors and Defenders wins`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5) { Warrior() }
        army1.addUnits(4) { Defender() }
        army1.addUnits(5) { Defender() }
        army2.addUnits(4) { Warrior() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("8. Battle")
    fun `Army with mixed Defenders and Warriors wins`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5) { Defender() }
        army1.addUnits(20) { Warrior() }
        army2.addUnits(21) { Warrior() }
        army1.addUnits(4) { Defender() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("9. Battle")
    fun `Army with Warriors and Defenders in mixed order wins`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(10) { Warrior() }
        army1.addUnits(5) { Defender() }
        army2.addUnits(5) { Warrior() }
        army1.addUnits(10) { Defender() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("10. Battle")
    fun `Small mixed army loses to larger Warrior army`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(2) { Defender() }
        army1.addUnits(1) { Warrior() }
        army1.addUnits(1) { Defender() }
        army2.addUnits(5) { Warrior() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("11. Battle")
    fun `Army with Defenders and Vampires loses`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5) { Defender() }
        army1.addUnits(6) { Vampire() }
        army1.addUnits(7) { Warrior() }
        army2.addUnits(6) { Warrior() }
        army2.addUnits(6) { Defender() }
        army2.addUnits(6) { Vampire() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("12. Battle")
    fun `Mixed army with fewer Vampires loses`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(2) { Defender() }
        army1.addUnits(3) { Vampire() }
        army1.addUnits(4) { Warrior() }
        army2.addUnits(4) { Warrior() }
        army2.addUnits(4) { Defender() }
        army2.addUnits(3) { Vampire() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("13. Battle")
    fun `Army with more Defenders wins against Vampire army`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(11) { Defender() }
        army1.addUnits(3) { Vampire() }
        army1.addUnits(4) { Warrior() }
        army2.addUnits(4) { Warrior() }
        army2.addUnits(4) { Defender() }
        army2.addUnits(13) { Vampire() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("14. Battle")
    fun `Army with Defenders and Warriors wins`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(9) { Defender() }
        army1.addUnits(3) { Vampire() }
        army1.addUnits(8) { Warrior() }
        army2.addUnits(4) { Warrior() }
        army2.addUnits(4) { Defender() }
        army2.addUnits(13) { Vampire() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("15. Battle")
    fun `Army with Lancers loses to balanced enemy army`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(5) { Lancer() }
        army1.addUnits(3) { Vampire() }
        army1.addUnits(4) { Warrior() }
        army1.addUnits(2) { Defender() }
        army2.addUnits(4) { Warrior() }
        army2.addUnits(4) { Defender() }
        army2.addUnits(6) { Vampire() }
        army2.addUnits(5) { Lancer() }
        assertFalse(fight(army1, army2))
    }

    @Test
    @DisplayName("16. Battle")
    fun `Army with more Lancers wins`() {
        val army1 = Army()
        val army2 = Army()
        army1.addUnits(7) { Lancer() }
        army1.addUnits(3) { Vampire() }
        army1.addUnits(4) { Warrior() }
        army1.addUnits(2) { Defender() }
        army2.addUnits(4) { Warrior() }
        army2.addUnits(4) { Defender() }
        army2.addUnits(6) { Vampire() }
        army2.addUnits(4) { Lancer() }
        assertTrue(fight(army1, army2))
    }

    @Test
    @DisplayName("17. Battle")
    fun `Warriors lose to Lancer army with backup`() {
        val armyWarrior = Army()
        val armyLancer = Army()
        armyWarrior.addUnits(2) { Warrior() }
        armyLancer.addUnits(1) { Lancer() }
        armyLancer.addUnits(1) { Warrior() }
        assertFalse(fight(armyWarrior, armyLancer))
    }
}