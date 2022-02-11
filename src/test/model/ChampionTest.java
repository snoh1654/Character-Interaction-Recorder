package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChampionTest {
    private Champion champion;
    private Champion editedChampion;
    private Champion championWithTwoOpposingInfo;
    private OpposingChampion opposingChampion;
    private OpposingChampion opposingChampion2;

    @BeforeEach
    void runBefore() {
        champion = new Champion("Test Champion", 5);
        editedChampion = new Champion("Test Champion 2", 10);
        championWithTwoOpposingInfo = new Champion("Test Champion 3", 3);
        opposingChampion = new OpposingChampion("Opposing Champion Tester", 6);
        opposingChampion2 = new OpposingChampion("Opposing Champion Tester 2", 9);
        editedChampion.addChampionInteraction(opposingChampion);
        championWithTwoOpposingInfo.addChampionInteraction(opposingChampion);
        championWithTwoOpposingInfo.addChampionInteraction(opposingChampion2);
    }

    @Test
    void addChampionInteractionTest() {
        assertTrue(champion.getInteractionList().isEmpty());
        champion.addChampionInteraction(opposingChampion);
        assertEquals(opposingChampion, champion.getInteractionList().get(0));
    }

    @Test
    void removeChampionInteractionWorkingTest() {
        assertEquals(opposingChampion, editedChampion.getInteractionList().get(0));
        assertEquals("Done!", editedChampion.removeChampionInteraction
                ("Opposing Champion Tester"));
        assertTrue(editedChampion.getInteractionList().isEmpty());
    }

    @Test
    void removeChampionInteractionFailingTest() {
        assertEquals(opposingChampion, editedChampion.getInteractionList().get(0));
        assertEquals("Champion is not in Interaction List.", editedChampion.removeChampionInteraction
                ("Incorrect Name"));
        assertFalse(editedChampion.getInteractionList().isEmpty());
    }

    @Test
    void removeChampionInteractionFailingTestNoName() {
        assertEquals("Champion is not in Interaction List.",
                champion.removeChampionInteraction("Opposing Champion Tester"));
        assertFalse(editedChampion.getInteractionList().isEmpty());
    }

    @Test
    void editChampionInteractionWorkingTest() {
        assertEquals(opposingChampion, editedChampion.getInteractionList().get(0));
        assertEquals(6, editedChampion.getInteractionList().get(0).getDifficultyToPlayAgainst());
        assertEquals("", editedChampion.getInteractionList().get(0).getInteractionDetail());
        assertEquals("Updated Interaction List.", editedChampion.editChampionInteraction(
                "Opposing Champion Tester","Cool Information!"));
        assertEquals("Cool Information!", editedChampion.getInteractionList().get(0).getInteractionDetail());
    }

    @Test
    void editChampionInteractionFailingTest() {
        assertEquals(opposingChampion, editedChampion.getInteractionList().get(0));
        assertEquals(6, editedChampion.getInteractionList().get(0).getDifficultyToPlayAgainst());
        assertEquals("", editedChampion.getInteractionList().get(0).getInteractionDetail());
        assertEquals("Champion is not in Interaction List.", editedChampion.editChampionInteraction(
                "Fail", "Cool Information!"));
        assertEquals("", editedChampion.getInteractionList().get(0).getInteractionDetail());
    }

    @Test
    void getChampionInteractionTest() {
        assertEquals("Opposing Champion Tester\nDifficulty to Face: 6\nInteraction Details: \n\n" +
                "Opposing Champion Tester 2\nDifficulty to Face: 9\nInteraction Details: ",
                championWithTwoOpposingInfo.viewChampionInteraction());
    }

    @Test
    void editChampionInfoTest() {
        assertEquals("", editedChampion.getChampionInfo());
        editedChampion.editChampionInfo("Test Champion 2 is a character in League of Legends.");
        assertEquals("Test Champion 2 is a character in League of Legends.", editedChampion.getChampionInfo());
    }

    @Test
    void getNameTest() {
        assertEquals("Test Champion", champion.getName());
    }

    @Test
    void getDifficultyTest() {
        assertEquals(5, champion.getDifficulty());
    }
}