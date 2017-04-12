package com.vogella.android.test.juntexamples;

import com.vogella.android.test.juntexamples.model.Movie;
import com.vogella.android.test.juntexamples.model.Race;
import com.vogella.android.test.juntexamples.model.Ring;
import com.vogella.android.test.juntexamples.model.TolkienCharacter;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.vogella.android.test.juntexamples.model.Race.HOBBIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vogella on 19.06.16.
 */

public class DataModelTests {

    private DataService dataService;

    @Test
    public void validateTolkeinCharactorsInitializationWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        assertEquals(frodo.age, 33);
        assertEquals(frodo.getName(), "Frodo");
        assertNotEquals(frodo.getName(), "Frodon");
    }

    @Test
    public void checkEquals(){
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake",43, HOBBIT);
        assertEquals(jake, jakeClone);
    }


    @Test
    public void checkInheritance() {
        //find better assertions
        DataService dataService = new DataService();
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // checkthat tolkienCharacter.getClass is not a movie class
        assertTrue(!tolkienCharacter.getClass().equals(Movie.class) );
    }

    @Test
    public void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that Frodo and Gandalf are part of the fellowship
        assertTrue(fellowship.contains(dataService.frodo)&& fellowship.contains(dataService.gandalf));
    }

    @Test
    public void testThatOneRingBearerIsPartOfTheFellowship() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        Map<Ring, TolkienCharacter> ringBearers = dataService.getRingBearers();
        boolean oneRingBearer = false;
        for (TolkienCharacter character : ringBearers.values()) {
            if (fellowship.contains(character)) {
                oneRingBearer = true;
            }
        }
        // test that at least one ring bearer is part of the fellowship
        assertTrue(oneRingBearer);
    }

    @Test
    public void ensureOrdering() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        //frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir

        assertEquals(fellowship.get(0),dataService.frodo);
        assertEquals(fellowship.get(1),dataService.sam);
        assertEquals(fellowship.get(2),dataService.merry);
        assertEquals(fellowship.get(3),dataService.pippin);
        assertEquals(fellowship.get(4),dataService.gandalf);
        assertEquals(fellowship.get(5),dataService.legolas);
        assertEquals(fellowship.get(6),dataService.gimli);
        assertEquals(fellowship.get(7),dataService.aragorn);
        assertEquals(fellowship.get(8),dataService.boromir);
    }

    @Test
    public void ensureAge() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        for (TolkienCharacter character : fellowship) {
            if (character.getRace().equals(Race.HOBBIT) || character.getRace().equals(Race.MAN) ) {
                assertTrue(character.age < 100);
            } else {
                assertTrue(character.age > 100);

            }
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void ensureThatFellowsStayASmallGroup() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // Write a test to ensure that this thrown an IndexOutOfBoundsException
        fellowship.get(100);
    }





}
