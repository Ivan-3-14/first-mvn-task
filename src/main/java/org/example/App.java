package org.example;

import org.example.person.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.utility.Constant.*;

public class App {
    public static void main(String[] args) {

        List<Person> firstListOfPerson = Person.createPerson(COUNT_OF_PERSONS);

        List<Person> secondList =
                firstListOfPerson
                        .stream()
                        .filter(person -> person.getAge() < MAX_AGE_TO_FILTER)
                        .peek(System.out::println)
                        .sorted(Comparator.comparing(Person::getSurname)
                                .thenComparing(Person::getName))
                        .distinct()
                        .collect(Collectors.toList());

        writePersonsInFile(secondList, PATH_TO_FILE);

        List<Person> personsFromFile = readPersonsFromFile(PATH_TO_FILE);

        System.out.println(" ");
        System.out.println("result: ");
        System.out.println();
        List<String> result = personsFromFile.stream()
                .map((person -> person.getSurname() + " " + person.getName() + " "))
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public static void writePersonsInFile(List<Person> list, String pathToFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(Paths.get(pathToFile)))) {
            try {
                for (Person element : list) {
                    oos.writeObject(element);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> readPersonsFromFile(String pathToFile) {

        List<Person> list = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(pathToFile)))) {

            while (true) {
                try {
                    list.add((Person) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
