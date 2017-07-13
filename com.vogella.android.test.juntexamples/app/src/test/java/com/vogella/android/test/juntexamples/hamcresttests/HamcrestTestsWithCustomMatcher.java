package com.vogella.android.test.juntexamples.hamcresttests;

import org.junit.Test;

import static com.vogella.android.test.juntexamples.hamcresttests.RegexMatcher.matchesRegex;
import static org.hamcrest.MatcherAssert.assertThat;


public class HamcrestTestsWithCustomMatcher {


    @Test
    public void testRegularExpression( ) {
        String s ="aaabbbaaaa";
        // ensure that it matches "a*b*a*"
        assertThat(s, matchesRegex("a*b*a*"));
    }

}
