
package com.vogella.android.testing.applicationtest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class MyApplicationTest {
    @Test
    public void listShouldInitiallyBeEmpty() {
        assertThat(MyApplication.list, hasSize(0));
    }
}
