package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.person.Person;

import java.io.File;
import java.util.List;


public class AppTest extends TestCase {

    public static final String PATH = "src\\test\\java\\org\\example\\person\\File";
    public static final int COUNT_OF_ASSERTS_PERSONS = 100;


    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }

    public void testWriteAndReadPersonsInFile() {
        File file = new File(PATH);
        List<Person> listToFile = Person.createPerson(COUNT_OF_ASSERTS_PERSONS);
        App.writePersonsInFile(listToFile, PATH);
        List<Person> listFromFile = App.readPersonsFromFile(PATH);
        assertEquals(listToFile.size(), listFromFile.size());

        for (int i = 0; i < listToFile.size(); i++) {
            assertEquals(listToFile.get(i), listFromFile.get(i));
        }

        if (file.exists()) {
            file.delete();
        }
    }
}
