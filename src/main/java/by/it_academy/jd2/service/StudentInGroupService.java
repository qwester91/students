package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.StudentsInGroupDao;
import by.it_academy.jd2.dto.GroupWithStudents;
import by.it_academy.jd2.service.api.IService;

import java.util.List;

public class StudentInGroupService{
       private StudentsInGroupDao dao = StudentsInGroupDao.getInstance();

       private static final StudentInGroupService instance = new StudentInGroupService();

    private StudentInGroupService() {
    }

    public GroupWithStudents getGroup(int idGroup) {
        GroupWithStudents groupWithStudent = dao.getGroupWithStudent(idGroup);
        return groupWithStudent;
    }


    public void setGroup(GroupWithStudents group) {
        dao.setGroupWithStudent(group);

    }


    public void deleteAllStudentsFromGroup(int idGroup) {
        dao.deleteAllStudentsFromGroup(idGroup);
    }


    public void deleteStudents(GroupWithStudents item) {
        dao.deleteSomeStudentsFromGroup(item);

    }

    public static StudentInGroupService getInstance() {
        return instance;
    }
}
