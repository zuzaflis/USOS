package com.example.usos.StudentMethods;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "`Group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "max_number_of_students")
    private int maxNumberOfStudents;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> listOfStudents = new ArrayList<>();

    public Group() {}
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    @Override
    public String toString() {
        return  groupName;
    }

    public Group(String groupName, List<Student> listOfStudents, int maxNumberOfStudents) {
        this.groupName = groupName;
        this.listOfStudents = listOfStudents;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }
    public void removeStudent(Student student) {
        listOfStudents.remove(student);
    }
    public List<Student> getStudents(){
        return listOfStudents;
    }
    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public int getNumberOfStudents(){
        return listOfStudents.size();
    }
    public void addStudent(Student newStudent){
        if(listOfStudents.contains(newStudent)){
            System.err.println("Podany student już istnieje na liscie");
        }
        else if(listOfStudents.size()!=maxNumberOfStudents) {
            listOfStudents.add(newStudent);
        } else System.err.println("Brak miejsca na liscie studentów");
    }
    public void getStudent(Student student){
        if(student.getPoints()==0){
            listOfStudents.remove(student);
        }
    }
    public void addPoints(Student student, double points){
        student.setPoints(student.getPoints()+points);
    }
    public void removePoints(Student student, double points){
        student.setPoints(student.getPoints()-points);
    }
    public void changeCondition(Student student, StudentCondition newCondition){
        student.setStudentCondition(newCondition);
    }
    public Student search(String lastName){
        Comparator<Student> lastNameComparator = Comparator.comparing(Student::getLastName);
        Collections.sort(listOfStudents,lastNameComparator);
        Student studentToFind= new Student("",lastName,StudentCondition.CATCH_UP,0,0, "");
        int index =Collections.binarySearch(listOfStudents, studentToFind,lastNameComparator);

        if(index>=0){
            return listOfStudents.get(index);
        } else return null;
    }
    public int countByCondition(StudentCondition condition){
        int count =0;
        for (Student student : listOfStudents){
            if(student.getStudentCondition()==condition) {
                count++;
            }
        }
        return count;
    }
    public void summary() {
        System.out.println("Grupa: " + groupName);
        System.out.println("Liczba studentów: " + listOfStudents.size());
        System.out.println("Maksymalna liczba studentów: " + maxNumberOfStudents);
        System.out.println("Studenci:");
        for (Student student : listOfStudents) {
            System.out.println(student.toString());
        }
    }
    public void sortByName(){
        Comparator<Student> nameComparator= Comparator.comparing(Student::getName);
        Collections.sort(listOfStudents,nameComparator);
    }
    public List<Student> sortPartial(String part){
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student: listOfStudents){
            if(student.getName().contains(part)||student.getLastName().contains(part)){
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }
    public void sortByPoints(){
        Comparator<Student> pointsComparator = Comparator.comparing((Student::getPoints)).reversed();//dzięki reversed sortuje malejąco
        Collections.sort(listOfStudents,pointsComparator);
    }
    public void maxPoints(){
        Comparator<Student> pointsComparator = Comparator.comparing((Student::getPoints));
        Student studentWithMaxPoints= Collections.max(listOfStudents,pointsComparator);
        System.out.println("Student with max points: "+ studentWithMaxPoints);
    }
}
