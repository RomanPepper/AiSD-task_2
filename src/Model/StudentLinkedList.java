package Model;

import java.util.ArrayList;
import java.util.List;

public class StudentLinkedList {
    private Student head;
    private int size;

    public StudentLinkedList() {
        this.size = 0;
    }

    public StudentLinkedList(Student headNode) {
        this.head = headNode;
        this.size = 1;
    }

    public StudentLinkedList(Student[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            this.insert(i, inputArray[i]);
        }

        this.size = inputArray.length;
    }

    public StudentLinkedList(List<Student> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            this.insert(i, inputList.get(i));
        }

        this.size = inputList.size();
    }

    public void format() {
        //Будем считать, что студент 18-ти лет находится на первом курсе, 19-ти на втором и т.д.
        if (!(this.head == null || this.head.getNextNode() == null)) {
            //Пусть по нулевому индексу будет список студентов первого курса и т.д.
            List<List<Student>> studentListInCourses = new ArrayList<>();

            for(int i = 0; i < 6; i++) {
                List<Student> supportList = new ArrayList<>();
                studentListInCourses.add(supportList);
            }

            Student currentNode = this.head;
            for (int i = 0; i < this.size; i++) {
                int currentStudentCourse = currentNode.getAge() - 18;

                studentListInCourses.get(currentStudentCourse).add(currentNode);

                currentNode = currentNode.getNextNode();
            }

            //Теперь список студентов по курсам упорядочен в порядке неубывания курсов
            List<Student> finalStudentList = new ArrayList<>();
            for(List<Student> currCourse: studentListInCourses) {
                for(Student currStudent: currCourse) {
                    finalStudentList.add(currStudent);
                }
            }

            this.updateFromList(finalStudentList);
        }
    }

    public void updateFromList(List<Student> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            this.insert(i, inputList.get(i));
        }

        this.size = inputList.size();
    }

    public void takeCopyOf(StudentLinkedList copiedList) {
        this.head = copiedList.getHead();
        this.size = copiedList.size;
    }

    public void insertFirst(Student node) {
        node.setNextNode(this.head);
        this.head = node;
        this.size++;
    }

    public void insert(int index, Student node) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0) {
            this.insertFirst(node);
        } else if (index == this.size - 1) {
            this.insertFirst(node);
        } else {
            this.get(index - 1).setNextNode(node);
            node.setNextNode(this.get(index + 1));
        }

        this.size++;
    }

    public void insertLast(Student node) {
        Student lastNode = this.getLast();
        lastNode.setNextNode(node);
        this.size++;
    }

    public void removeFirst() {
        this.head = this.head.getNextNode();
        this.size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0) {
            this.removeFirst();
        } else if (index == this.size - 1) {
            this.removeLast();
        } else {
            this.get(index - 1).setNextNode(this.get(index + 1));
        }

        this.size--;
    }

    public void removeLast() {
        Student lastNode = this.getLast();
        this.size--;
    }

    public Student getFirst() {
        return head;
    }

    public Student get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        Student currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode;
    }

    public Student getLast() {
        Student currentNode = this.head;

        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode;
    }

    public void printList() {
        Student currentNode = this.head;

        while (currentNode != null) {
            currentNode.printStudent();
            currentNode = currentNode.getNextNode();
        }

        System.out.println("null");
    }

    public void setHead(Student head) {
        this.head = head;
    }

    public Student getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }
}
