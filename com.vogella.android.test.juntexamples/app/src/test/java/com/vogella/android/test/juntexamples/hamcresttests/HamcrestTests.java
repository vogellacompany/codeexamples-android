package com.vogella.android.test.juntexamples.hamcresttests;

import com.vogella.android.test.juntexamples.DataService;
import com.vogella.android.test.juntexamples.model.Race;
import com.vogella.android.test.juntexamples.model.TolkienCharacter;

import org.hamcrest.Condition;
import org.junit.Test;

import java.util.List;

import static com.vogella.android.test.juntexamples.model.Race.HOBBIT;
import static com.vogella.android.test.juntexamples.model.Race.ORC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.*;


/**
 * Created by vogella on 19.06.16.
 */

public class HamcrestTests {


    @Test
    public void validateTolkeinCharactorsInitializationWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        //age is 33
        //name is "Frodo"
        //name is not "Frodon"
        assertThat(frodo.getName(),is("Frodo"));
        assertThat(frodo.age,is(33));
        assertThat(frodo.getName(), is(not("Frodon")));

    }

    @Test
    public void ensureThatFellowsAreNotOrcs() {
        List<TolkienCharacter> fellowship = new DataService().getFellowship();
        // ensure that not any of the fellows is a orc

        for (TolkienCharacter t:
                fellowship) {
            assertThat(t.getRace(), not(is(ORC)));
        }
        assertThat(fellowship,not(anyOf()));
    }
}
