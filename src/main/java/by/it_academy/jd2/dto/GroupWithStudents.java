package by.it_academy.jd2.dto;

import java.util.List;

public class GroupWithStudents {
    private int idGroup;
    private List<Student> Students;

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public List<Student> getStudents() {
        return Students;
    }

    public void setStudents(List<Student> students) {
        Students = students;
    }




}
