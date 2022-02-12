package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OpposingChampionTest {
    private OpposingChampion opposingChampion;

    @BeforeEach
    void setup() {
        opposingChampion = new OpposingChampion("Testing Character", 5);
        opposingChampion.setInteractionDetail("Not much to say...");
    }

    @Test
    void editInteractionTest() {
        assertEquals("Not much to say...", opposingChampion.getInteractionDetail());
        opposingChampion.setInteractionDetail("Testing Character is strong against chosen character");
        assertEquals("Testing Character is strong against chosen character", opposingChampion.getInteractionDetail());
    }

    @Test
    void getNameTest() {
        assertEquals("Testing Character", opposingChampion.getName());
    }

    @Test
    void getDifficultyTest() {
        assertEquals(5, opposingChampion.getDifficultyToPlayAgainst());
    }

    @Test
    void toStringTest() {
        assertEquals("Testing Character\nDifficulty to Face: 5\nInteraction Details: Not much to say...",
                opposingChampion.toString());
    }
}
