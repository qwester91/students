package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IDao;
import by.it_academy.jd2.dto.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IDao<Student> {
    private static final StudentDao instance = new StudentDao();

    private StudentDao(){

    }

    @Override
    public List<Student> getListOfItem() {
        List<Student> studentList = new ArrayList<>();
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
    public Student getItem(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id,\n" +
                     "       name,\n" +
                     "       age,\n" +
                     "       score,\n" +
                     "       olympic_gamer\n" +
                     "FROM   jdbc.students\n" +
                     "WHERE  id = ?; ")){

             statement.setInt(1, id);
             ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void setItem(Student item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO jdbc.students(\n" +
                     "                name, age, score, olympic_gamer)\n" +
                     "        VALUES (?, ?, ?, ?);")){


            statement.setString(1, item.getName());
            statement.setInt(2, item.getAge());
            statement.setDouble(3, item.getScore());
            statement.setBoolean(4, item.isOlympicGamer());
            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItem(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM jdbc.students\n" +
                     "        WHERE  id = ?;")){

            statement.setInt(1, id);
            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateItem(Student item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE jdbc.students\n" +
                     "        SET name=?,\n" +
                     "        age=?,\n" +
                     "        score=?,\n" +
                     "        olympic_gamer=?\n" +
                     "        WHERE id =?;")){


            statement.setString(1, item.getName());
            statement.setInt(2, item.getAge());
            statement.setDouble(3, item.getScore());
            statement.setBoolean(4, item.isOlympicGamer());
            statement.setInt(5, item.getId());
            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private Student map(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setAge(rs.getInt("age"));
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympic_gamer"));

       return student;
    }

    public static StudentDao getInstance(){
        return instance;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
