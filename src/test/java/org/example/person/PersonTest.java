package org.example.person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    public static final String SURNAME = "SMITH";
    public static final String NAME = "ALEX";
    public static final int AGE = 20;
    public static final int COUNT_OF_ASSERTS_PERSONS = 200;

    private final Person person1 = new Person(SURNAME, NAME, AGE);
    private final Person person2 = new Person(SURNAME, NAME, AGE);

    @org.junit.jupiter.api.Test
    void testCreatePerson() {
        List<Person> list = Person.createPerson(COUNT_OF_ASSERTS_PERSONS);
        assertEquals(COUNT_OF_ASSERTS_PERSONS, list.size());

        for (Person element : list) {
            assertNotNull(element);
        }
    }

    @org.junit.jupiter.api.Test
    void testGetSurname() {
        assertEquals(SURNAME, person1.getSurname());
        assertEquals(person1.getSurname(), person2.getSurname());
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        assertEquals(NAME, person1.getName());
        assertEquals(person1.getName(), person2.getName());
    }

    @org.junit.jupiter.api.Test
    void testGetAge() {
        assertEquals(AGE, person1.getAge());
        assertEquals(person1.getAge(), person2.getAge());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertEquals(person1, person2);
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testCompareTo() {
        assertEquals(0, person1.compareTo(person2));

        Person person3 = new Person(SURNAME, NAME, AGE + 1);
        assertTrue(0 > person2.compareTo(person3));

        person3 = new Person(SURNAME, "A" + NAME, AGE);
        assertTrue(0 < person2.compareTo(person3));

        person3 = new Person("A" + SURNAME,  NAME, AGE);
        assertTrue(0 < person2.compareTo(person3));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("SMITH ALEX, age=20", person1.toString());
    }
}