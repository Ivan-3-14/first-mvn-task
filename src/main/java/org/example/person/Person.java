package org.example.person;

import org.example.enums.Name;
import org.example.enums.Surname;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.example.utility.Constant.*;

public class Person implements Serializable, Comparable<Person> {
    private final String surname;
    private final String name;
    private final int age;
    private final static Random RANDOM = new Random();

    public Person(String surname, String name, int age) {
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    public static List<Person> createPerson(int count) {
        List<Person> list = new ArrayList<>();
        int boundOfAge = MAX_AGE_OF_PERSON - MIN_AGE_OF_PERSON;

        for (int i = 0; i < count; i++) {
            String createdSurname = String.valueOf(Surname.values()[RANDOM.nextInt(Surname.values().length)]);
            String createdName = String.valueOf(Name.values()[RANDOM.nextInt(Name.values().length)]);
            int createdAge = RANDOM.nextInt(boundOfAge) + MIN_AGE_OF_PERSON;

            list.add(new Person(createdSurname, createdName, createdAge));
        }
        return list;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(surname, person.surname) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, age);
    }

    @Override
    public int compareTo(Person o) {
        if (this.getSurname().compareTo(o.getSurname()) == 0) {
            if (this.getName().compareTo(o.getName()) == 0) {
                return this.getAge() - o.getAge();
            }
            return this.getName().compareTo(o.getName());
        }
        return this.getSurname().compareTo(o.getSurname());
    }

    @Override
    public String toString() {
        return surname + " " + name + ", age=" + age;
    }
}
