package com.vogella.android.test.juntexamples;

import com.vogella.android.test.juntexamples.model.TolkienCharacter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.vogella.android.test.juntexamples.model.Race.HOBBIT;


public class DataModelTestUsingRules {
    //TODO You may need to fix your data model for these tests
    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Test
    public void testThatAgeMustBeLargerThanZeroViaSetter() {

        rule.expect(IllegalArgumentException.class);
        rule.expectMessage("Age is not allowed to be smaller than zero");
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        frodo.setAge(-1);

        rule.expect(IllegalArgumentException.class);
        rule.expectMessage("Hello");
        // another test with exmaple

    }

    @Test
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        rule.expect(IllegalArgumentException.class);
        rule.expectMessage("Age is not allowed to be smaller than zero");
        TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
    }
}
