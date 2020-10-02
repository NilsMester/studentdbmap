package de.neuefische.hh2020ji.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentDBTest {

    @Test
    void testList(){
        StudentDB studentDB = new StudentDB(
                List.of(
                    new Student("Jane Doe", 1),
                    new Student("John Doe", 2)
        ));

        List<Student> actual = studentDB.list();

        assertEquals(2, actual.size());

        assertEquals(new Student("Jane Doe", 1), actual.get(0));
        assertEquals(new Student("John Doe", 2), actual.get(1));

    }

    @Test
    void testToString(){
        StudentDB studentDB = new StudentDB(
                List.of(
                        new Student("Jane Doe", 1),
                        new Student("John Doe", 2)
                ));
        String expected = "StudentDB(\n"
                +"Student(name=Jane Doe, id=1)\n"
                +"Student(name=John Doe, id=2)\n"
                +")";

        String actual = studentDB.toString();

        assertEquals(expected, actual);
    }

    @Test
    void testAddStudent(){
        StudentDB studentDB = new StudentDB(
                List.of(
                        new Student("Jane Doe", 1),
                        new Student("John Doe", 2)
                ));

        studentDB.add(new Student("Molly Doe", 3));

        List<Student> actual = studentDB.list();

        assertEquals(3, actual.size());

        assertEquals(new Student("Jane Doe", 1), actual.get(0));
        assertEquals(new Student("John Doe", 2), actual.get(1));
        assertEquals(new Student("Molly Doe", 3), actual.get(2));

    }

    @Test
    void checkIfRuntimeExceptionIDWorks(){
        StudentDB studentDB = new StudentDB(
                List.of(
                        new Student("Jane Doe", 1),
                        new Student("John Doe", 2)
                ));
        try {
            studentDB.add(new Student("Klaus Meiser",1));
            fail("Exception not thrown!");
        }catch (RuntimeException e){
            String actual = e.getMessage();
            assertEquals("Die ID ist bereits belegt!", e.getMessage());
        }
    }

    @Test
    void testIfOptionalContainsStudent (){
        StudentDB studentDB = new StudentDB(
                List.of(
                        new Student("Jane Doe", 1),
                        new Student("John Doe", 2)
                ));
        Optional<Student> actual = studentDB.findById(2);
        assertTrue(actual.isPresent());
        assertEquals (new Student("John Doe", 2), actual.get());
    }
    @Test
    void testIfOptionalContainsNoStudent () {
        StudentDB studentDB = new StudentDB(
                List.of(
                        new Student("Jane Doe", 1),
                        new Student("John Doe", 2)
                ));
        Optional<Student> actual = studentDB.findById(3);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testRemoveStudent(){
        StudentDB studentDB = new StudentDB(
                List.of(
                        new Student("Jane Doe", 1),
                        new Student("John Doe", 2),
                        new Student("Molly Doe", 3)
                ));

        studentDB.removeById(2);

       List<Student> actual = studentDB.list();

        assertEquals(2, actual.size());

        assertEquals(new Student("Jane Doe", 1), actual.get(0));
        assertEquals(new Student("Molly Doe", 3), actual.get(1));
    }



}