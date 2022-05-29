package by.it_academy.jd2.dao.api;

import by.it_academy.jd2.dto.GroupDtoForBase;
import by.it_academy.jd2.dto.GroupWithStudents;

import java.util.List;

public interface IGroupDao extends AutoCloseable{
    GroupWithStudents getGroupWithStudent(int idGroup);
    void setGroupWithStudent(GroupWithStudents group);
    void deleteAllStudentsFromGroup(GroupWithStudents group);
    void deleteSomeStudentsFromGroup(GroupWithStudents group);
}
