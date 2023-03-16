import Model.StudentLinkedList;
import View.StudentListDisplay;

public class Main {
    public static void main(String[] args) {
        StudentLinkedList studentLinkedList = new StudentLinkedList();

        StudentListDisplay windowApplication = new StudentListDisplay(studentLinkedList);



//        Student olga = new Student("olga", 18);
//        Student maria = new Student("maria", 20);
//        Student anna = new Student("anna", 21);
//        Student valera = new Student("valera", 18);
//
//        StudentLinkedList studentList = new StudentLinkedList(new Student[]{olga, maria, anna, valera});
//
//        studentList.printList();
    }
}