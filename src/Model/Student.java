package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int age;
    private Student nextNode;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, Student nextNode) {
        this.name = name;
        this.age = age;
        this.nextNode = nextNode;
    }

    public static List<Student> parseMapToList(Map<String, Integer> inputMap) {
        List<Student> studentList = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: inputMap.entrySet()) {
            String key = entry.getKey();
            studentList.add(new Student(key, inputMap.get(key)));
        }

        return studentList;
    }

    public void printStudent() {
        System.out.println("{name: " + name + "; age: " + age + "}\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getNextNode() {
        return nextNode;
    }

    public void setNextNode(Student nextNode) {
        this.nextNode = nextNode;
    }
}
