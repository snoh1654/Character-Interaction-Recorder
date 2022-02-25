package persistence;

import model.Champion;
import model.ChampionCollection;
import model.OpposingChampion;
import model.OpposingChampionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/\fds0doesNotExist.json");
            jsonWriter.begin();
            fail("IOException should occur");
        } catch (IOException e) {
            // test should pass
        }
    }

    @Test
    void testWriterEmptyFile() {
        try {
            ChampionCollection championCollection = new ChampionCollection();
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyChampionCollection.json");
            jsonWriter.begin();
            jsonWriter.write(championCollection);
            jsonWriter.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyChampionCollection.json");
            ChampionCollection comparedCollection =  reader.read();
            assertEquals(0, comparedCollection.getChampionsGuide().size());
        } catch (IOException e) {
            fail("IOException should not occur");
        }
    }

    @Test
    void testWriterRegularFile() {
        try {
            ChampionCollection championCollection = new ChampionCollection();
            Champion champion = new Champion("Test Champion", 4);
            champion.setChampionInfo("Test!");
            championCollection.addChampion(champion);
            OpposingChampion opposingChampion = new OpposingChampion("Opposing Champion", 6);
            opposingChampion.setInteractionDetail("Testing!");
            champion.addChampionInteraction(opposingChampion);

            JsonWriter writer = new JsonWriter("./data/testWriterRegularChampionCollection.json");
            writer.begin();
            writer.write(championCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRegularChampionCollection.json");
            ChampionCollection comparedCollection =  reader.read();

            ArrayList<Champion> readChampionCollection = comparedCollection.getChampionsGuide();
            assertEquals(1, readChampionCollection.size());

            Champion readChampion = readChampionCollection.get(0);
            assertEquals("Test Champion", readChampion.getName());
            assertEquals(4, readChampion.getDifficulty());
            assertEquals("Test!", readChampion.getChampionInfo());

            assertEquals(1, readChampion.getInteractionList().size());
            OpposingChampion readOpposingChampion = readChampion.getInteractionList().get(0);
            assertEquals("Opposing Champion", readOpposingChampion.getName());
            assertEquals(6, readOpposingChampion.getDifficultyToPlayAgainst());
            assertEquals("Testing!", readOpposingChampion.getInteractionDetail());
        }
        catch (IOException e) {
            fail("Exception should not occur");
        }
    }
}
