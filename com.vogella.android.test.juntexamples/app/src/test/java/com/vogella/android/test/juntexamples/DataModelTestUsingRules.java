package com.vogella.android.test.juntexamples;

import com.vogella.android.test.juntexamples.model.TolkienCharacter;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.vogella.android.test.juntexamples.model.Race.HOBBIT;


public class DataModelTestUsingRules {
    //TODO You may need to fix your data model for these tests
    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Test
    public void testThatAgeMustBeLargerThanZeroViaSetter() {
        rule.expect(IllegalArgumentException.class);
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        // use ExpectedException rule to check that the message is:
        // Age is not allowed to be smaller than zero
        frodo.setAge(-1);

//        rule.expectMessage("Age is not allowed to be smaller than zero");

    }

    @Test
    @Ignore
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        rule.expect(IllegalArgumentException.class);
        TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
        // use ExpectedException rule to check that the message is:
        // Age is not allowed to be smaller than zero

//        rule.expectMessage("Age is not allowed to be smaller than zero");

    }
}
