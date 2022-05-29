package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IGroupDao;
import by.it_academy.jd2.dto.GroupDtoForBase;
import by.it_academy.jd2.dto.GroupWithStudents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsInGroupDao implements IGroupDao {

    private static final StudentsInGroupDao instance = new StudentsInGroupDao();
    private StudentsInGroupDao(){

    }

    @Override
    public GroupWithStudents getGroupWithStudent() {
        GroupWithStudents group = new GroupWithStudents();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id,\n" +
                     "       name,\n" +
                     "       age,\n" +
                     "       score,\n" +
                     "       olympic_gamer\n" +
                     "FROM   jdbc.students; ")){


            while (resultSet.next()) {
                studentList.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public void setGroupWithStudent(GroupWithStudents group) {

    }

    @Override
    public void deleteAllStudentsFromGroup(GroupWithStudents group) {

    }

    @Override
    public void deleteSomeStudentsFromGroup(GroupWithStudents group) {

    }

    private GroupDtoForBase map(ResultSet rs) throws SQLException {
        GroupDtoForBase group = new GroupDtoForBase();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        return group;
    }

    public static StudentsInGroupDao getInstance(){
        return instance;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
