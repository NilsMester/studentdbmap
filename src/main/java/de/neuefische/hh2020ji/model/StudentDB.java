package de.neuefische.hh2020ji.model;

import java.lang.reflect.Array;
import java.util.*;

public class StudentDB {

    private Map<String,Student> studentsMap=new HashMap<>();

    public StudentDB(List<Student> students) {
        for (Student student: students){
            studentsMap.put(""+student.getId(),student);
        }
    }

    public List<Student> list(){
        Collection<Student> outCollection =studentsMap.values();
        List<Student>studentList=new ArrayList<>(outCollection);
            studentList.sort(Comparator.comparing(Student::getId));
        return studentList;
    }


    public Student randomStudent(){
        List<Student>studentsInMap=list();
        double random = Math.random();
        int randomIndex = (int) (random * studentsInMap.size());
        return studentsInMap.get(randomIndex);
    }

    @Override
    public String toString(){
        List<Student>studentsInMap=list();
        String result = "StudentDB(\n";
        for(Student student : studentsInMap) {
            result += student.toString() + "\n";
        }
        result += ")";
        return result;
    }

    public void add(Student student) {
        studentsMap.put(""+student.getId(),student);
    }


    public void removeById(int id) {
      studentsMap.remove(""+id);
    }


}
