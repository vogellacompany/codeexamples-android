
package com.vogella.android.test.juntexamples.model;


import static com.vogella.android.test.juntexamples.model.Alignment.GOOD;
import static com.vogella.android.test.juntexamples.model.Alignment.NEUTRAL;
import static com.vogella.android.test.juntexamples.model.Alignment.EVIL;

/**
 * Race in Tolkien's Lord of the Rings.
 *
 * @author Florent Biville
 */
public enum Race {

    HOBBIT("Hobbit", false, GOOD), MAIA("Maia", true, GOOD), MAN("Man", false, NEUTRAL), ELF("Elf", true, GOOD), DWARF("Dwarf", false, GOOD), ORC("Orc", false, EVIL);

    private final String name;
    private final boolean immortal;
    private Alignment alignment;

    Race(String name, boolean immortal, Alignment alignment) {
        this.name = name;
        this.immortal = immortal;
        this.alignment = alignment;
    }

    public String getName() {
        return name;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    @Override
    public String toString() {
        return "Race [name=" + name + ", immortal=" + immortal + "]";
    }
}
