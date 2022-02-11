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
        opposingChampion.editInteractionDetail("Testing!");
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
    void changeChampionInfoWorkingTest() {
        assertEquals("Not Much Yet!", bigCollection.getChampionsGuide().get(1).getChampionInfo());
        assertEquals("Test Champion 2's information was changed.",
                bigCollection.changeChampionInfo("Test Champion 2","changed"));
        assertEquals("changed",bigCollection.getChampionsGuide().get(1).getChampionInfo());
    }

    @Test
    void changeChampionInfoFailingTest() {
        assertEquals("", bigCollection.getChampionsGuide().get(0).getChampionInfo());
        assertEquals("Not Much Yet!", bigCollection.getChampionsGuide().get(1).getChampionInfo());
        assertEquals("That Champion does not exist in the collection.",
                bigCollection.changeChampionInfo("Fail","changed"));
        assertEquals("",bigCollection.getChampionsGuide().get(0).getChampionInfo());
        assertEquals("Not Much Yet!",bigCollection.getChampionsGuide().get(1).getChampionInfo());
    }

    @Test
    void createChampionInteractionWorkingTest() {
        assertEquals("Test Champion", bigCollection.getChampionsGuide().get(0).getName());
        assertEquals("Added new Interaction Information for Test Champion.",
                bigCollection.createChampionInteraction("Test Champion", opposingChampion));
        assertEquals(opposingChampion, bigCollection.getChampionsGuide().get(0).getInteractionList().get(0));
    }

    @Test
    void createChampionInteractionFailingTest() {
        assertEquals(0, bigCollection.getChampionsGuide().get(0).getInteractionList().size());
        assertEquals("Test Champion 3 does not exist in the collection.",
                bigCollection.createChampionInteraction("Test Champion 3", opposingChampion));
        assertEquals(0, bigCollection.getChampionsGuide().get(0).getInteractionList().size());
    }

    @Test
    void changeChampionInteractionWorkingTest() {
        assertEquals("Testing!", bigCollection.getChampionsGuide().get(1).getInteractionList()
                .get(0).getInteractionDetail());
        assertEquals("Interaction Information Changed.", bigCollection.changeChampionInteraction
                ("Test Champion 2", "Opposing Champion Tester", "changed"));
        assertEquals("changed", bigCollection.getChampionsGuide().get(1).getInteractionList().get(0)
                .getInteractionDetail());
    }

    @Test
    void changeChampionInteractionFailingTest() {
        assertEquals("Testing!", bigCollection.getChampionsGuide().get(1).getInteractionList()
                .get(0).getInteractionDetail());

        assertEquals("Either the Champion or Opposing Champion does not exist.",
                bigCollection.changeChampionInteraction("Test Champion 3",
                        "Opposing Champion Tester", "changed"));

        assertEquals("Testing!", bigCollection.getChampionsGuide().get(1).getInteractionList().get(0)
                .getInteractionDetail());
    }

    @Test
    void listAvailableChampionsTest() {
        assertEquals("Test Champion, Test Champion 2, ", bigCollection.listAvailableChampions());
    }

    @Test
    void viewChampionInfoPassingTest() {
        assertEquals("Test Champion 2\nDifficulty: 5\nInfo: Not Much Yet!\nInteractions with Other Champions:" +
                        "\n\nOpposing Champion Tester\nDifficulty to Face: 6\nInteraction Details: Testing!"
                        + "\n\nOpposing Champion Tester 2\nDifficulty to Face: 9\nInteraction Details: \n\n"
                ,bigCollection.viewChampion("Test Champion 2"));
    }

    @Test
    void viewChampionInfoFailingTest() {
        assertEquals("That Champion is not in the Collection.",bigCollection.viewChampion("Fail"));
    }

}
