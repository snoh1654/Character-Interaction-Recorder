package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChampionCollectionTest {
    private ChampionCollection emptyCollection;
    private ChampionCollection notEmptyCollection;
    private ChampionCollection bigCollection;
    private Champion champion;
    private Champion champion2;
    private OpposingChampion opposingChampion;
    private OpposingChampion opposingChampion2;

    @BeforeEach
    void setup() {
        emptyCollection = new ChampionCollection();
        notEmptyCollection = new ChampionCollection();
        bigCollection = new ChampionCollection();
        champion = new Champion("Test Champion", 4);
        champion2 = new Champion("Test Champion 2", 5);
        notEmptyCollection.addChampion(champion);
        bigCollection.addChampion(champion);
        bigCollection.addChampion(champion2);
        opposingChampion = new OpposingChampion("Opposing Champion Tester", 6);
        opposingChampion2 = new OpposingChampion("Opposing Champion Tester 2", 9);
        champion2.addChampionInteraction(opposingChampion);
        champion2.addChampionInteraction(opposingChampion2);
        champion2.editChampionInfo("Not Much Yet!");
    }

    @Test
    void addChampionTest() {
        assertTrue(emptyCollection.getChampionsGuide().isEmpty());
        emptyCollection.addChampion(champion);
        assertEquals(champion, emptyCollection.getChampionsGuide().get(0));
    }

    @Test
    void removeChampionWorkingTest() {
        assertEquals("Test Champion", notEmptyCollection.getChampionsGuide().get(0).getName());
        assertEquals("Removed the Champion.", notEmptyCollection.removeChampion("Test Champion"));
        assertTrue(notEmptyCollection.getChampionsGuide().isEmpty());
    }

    @Test
    void removeChampionFailingTestInvalidName() {
        assertEquals("Test Champion", notEmptyCollection.getChampionsGuide().get(0).getName());
        assertEquals("That Champion is not in the Collection.",
                notEmptyCollection.removeChampion("Fail"));
        assertFalse(notEmptyCollection.getChampionsGuide().isEmpty());
    }

    @Test
    void listAvailableChampionsTest() {
        assertEquals("Test Champion, Test Champion 2", bigCollection.listAvailableChampions());
    }

    @Test
    void viewChampionInfoPassingTest() {
        assertEquals("Test Champion 2\nDifficulty: 5\nInfo: Not Much Yet!\nInteractions with Other Champions:\n"
                        + "Opposing Champion Tester\nDifficulty to Face: 6\nInteraction Details: "
                        + "\n\nOpposing Champion Tester 2\nDifficulty to Face: 9\nInteraction Details: "
                ,bigCollection.viewChampion("Test Champion 2"));
    }

    @Test
    void viewChampionInfoFailingTest() {
        assertEquals("That Champion is not in the Collection.",bigCollection.viewChampion("Fail"));
    }

}
