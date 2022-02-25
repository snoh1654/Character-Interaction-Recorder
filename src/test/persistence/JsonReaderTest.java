package persistence;

import model.Champion;
import model.ChampionCollection;
import model.OpposingChampion;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        try {
            JsonReader reader = new JsonReader("./data/doesNotExist.json");
            ChampionCollection championCollection = reader.read();
            fail("IOException should occur");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyChampionCollection() {
        JsonReader jsonReader = new JsonReader("./data/testReaderEmptyChampionCollection.json");

        try {
            ChampionCollection championCollection = jsonReader.read();
            assertEquals(0, championCollection.getChampionsGuide().size());
        } catch (IOException e) {
            fail("IOException should not occur");
        }
    }

    @Test
    void testReaderRegularChampionCollection() {
        JsonReader jsonReader = new JsonReader("./data/testReaderRegularChampionCollection.json");

        try {
            ChampionCollection championCollection = jsonReader.read();
            assertEquals(1, championCollection.getChampionsGuide().size());

            Champion readChampion = championCollection.getChampionsGuide().get(0);
            assertEquals("Test", readChampion.getName());
            assertEquals(8, readChampion.getDifficulty());
            assertEquals("Currently Testing", readChampion.getChampionInfo());

            assertEquals(2, readChampion.getInteractionList().size());
            OpposingChampion opposingChampion1 = readChampion.getInteractionList().get(0);
            OpposingChampion opposingChampion2 = readChampion.getInteractionList().get(1);
            assertEquals("Opposing Champion1", opposingChampion1.getName());
            assertEquals(3, opposingChampion1.getDifficultyToPlayAgainst());
            assertEquals("Testing 1", opposingChampion1.getInteractionDetail());
            assertEquals("Opposing Champion2", opposingChampion2.getName());
            assertEquals(6, opposingChampion2.getDifficultyToPlayAgainst());
            assertEquals("Testing 2", opposingChampion2.getInteractionDetail());
        } catch (IOException e) {
            fail("IOException should not occur");
        }
    }
}
