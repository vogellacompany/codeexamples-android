
package com.vogella.android.test.juntexamples.model;

/**
 * A simple class to illustrate AssertJ assertions.
 */
public class TolkienCharacter {

    // public to test extract on field
    public int age;
    private String name;
    private Race race;
    // not accessible field to test that field by field comparison does not use it
    @SuppressWarnings("unused")
    private long notAccessibleField = System.currentTimeMillis();

    public TolkienCharacter(String name, int age, Race race) {
        super();
        this.name = name;
        this.age = age;
        this.race = race;
    }

    public Race getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age<0) {
            throw new IllegalArgumentException("Age is not allowed to be smaller than zero");
        }
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((race == null) ? 0 : race.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TolkienCharacter other = (TolkienCharacter) obj;
        if (age != other.age) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (race == null) {
            if (other.race != null) return false;
        } else if (!race.equals(other.race)) return false;
        return true;
    }

    @Override
    public String toString() {
        return name + " " + age + " years old " + race.getName();
    }

}
