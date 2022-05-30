package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IGroupDao;
import by.it_academy.jd2.dto.GroupDtoForBase;
import by.it_academy.jd2.dto.GroupWithStudents;
import by.it_academy.jd2.dto.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsInGroupDao implements IGroupDao {

    private static final StudentsInGroupDao instance = new StudentsInGroupDao();
    private StudentsInGroupDao(){

    }

    @Override
    public GroupWithStudents getGroupWithStudent(int idGroup) {
        GroupWithStudents group;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT g.id AS group_id,\n" +
                     "       g.name AS group_name,\n" +
                     "       s.id AS student_id,\n" +
                     "       s.name AS student_name,\n" +
                     "       s.age AS student_age,\n" +
                     "       s.score AS student_score,\n" +
                     "       s.olympic_gamer AS student_olympic\n" +
                     "FROM jdbc.cross_table AS ct\n" +
                     "JOIN jdbc.groups AS g ON ct.id_group = g.id\n" +
                     "JOIN jdbc.students AS s ON ct.id_student = s.id\n" +
                     "WHERE g.id = ?;")){

            statement.setInt(1, idGroup);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            group = map(resultSet);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }

    @Override
    public void setGroupWithStudent(GroupWithStudents group) {
        int idGroup = group.getIdGroup().getId();
        for (Student student : group.getStudents()) {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO jdbc.cross_table\n" +
                         "            (id_group,\n" +
                         "             id_student)\n" +
                         "VALUES      (?, ?);  ")) {


                statement.setInt(1, idGroup);
                statement.setInt(2, student.getId());
                statement.execute();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteAllStudentsFromGroup(int idGroup) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM jdbc.cross_table\n" +
                     "        WHERE  id_group = ?;")){

            statement.setInt(1, idGroup);
            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteSomeStudentsFromGroup(GroupWithStudents group) {
        for (Student student : group.getStudents()) {


            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM jdbc.cross_table\n" +
                         "        WHERE  id_student = ?;")) {

                statement.setInt(1, student.getId());
                statement.execute();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private GroupWithStudents map(ResultSet rs) throws SQLException {
        GroupDtoForBase group = new GroupDtoForBase();
        List<Student> listOfStudentsInGroup = new ArrayList<>();

        boolean isGroupSet = false;
            while (rs.next()) {
                if (!isGroupSet){
                    group.setId(rs.getInt("group_id"));
                    group.setName(rs.getString("group_name"));
                    isGroupSet = true;
                }
                Student student = new Student();
                student.setAge(rs.getInt("student_age"));
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("student_name"));
                student.setScore(rs.getDouble("student_score"));
                student.setOlympicGamer(rs.getBoolean("student_olympic"));
                listOfStudentsInGroup.add(student);
            }
            GroupWithStudents groupWithStudents = new GroupWithStudents();
            groupWithStudents.setIdGroup(group);
            groupWithStudents.setStudents(listOfStudentsInGroup);
        return groupWithStudents;
    }

    public static StudentsInGroupDao getInstance(){
        return instance;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
