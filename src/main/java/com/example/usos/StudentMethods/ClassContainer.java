package com.example.usos.StudentMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    private final Map<String, Group> groups;

    public Map<String, Group> getGroups() {
        return groups;
    }

    public ClassContainer() {
        groups = new HashMap<>();
    }

    public void addClass(String groupName, int maxNumber) {
        List<Student> listOfStudents= new ArrayList<>();
        Group newClass = new Group(groupName, listOfStudents,maxNumber);
        newClass.setMaxNumberOfStudents(maxNumber);

        groups.put(groupName, newClass);
    }

    public void removeClass(String nameOfClassToRemove) {
        groups.remove(nameOfClassToRemove);
    }

    public List<String> findEmpty() {
        List<String> emptyClasses = new ArrayList<>();
        for (String groupName : groups.keySet()) {
            Group group = groups.get(groupName);
            if (group.getNumberOfStudents() == 0) {
                emptyClasses.add(groupName);
            }
        }
        return emptyClasses;
    }

    @Override
    public String toString() {
        return "ClassContainer{" +
                "groups=" + groups +
                '}';
    }

    public void summary() {
        for (String groupName : groups.keySet()) {
            Group group = groups.get(groupName);
            if(group!=null){
                double percent= ((double)group.getNumberOfStudents()/group.getMaxNumberOfStudents()) *100;
                System.out.println("Grupa: " + groupName + ", zapełnienie: " + String.format("%.2f%%", percent));
            } else {
                System.out.println("Grupa: " + groupName + ", zapełnienie: 0%");
            }
        }
    }
}