package by.it_academy.jd2;

import by.it_academy.jd2.dto.GroupDtoForBase;
import by.it_academy.jd2.dto.GroupWithStudents;
import by.it_academy.jd2.dto.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Student student = new Student();
        student.setAge(12);
        student.setId(1);
        student.setName("pkds");
        student.setScore(3.2);
        student.setOlympicGamer(true);

        Student student1 = new Student();
        student1.setAge(112);
        student1.setId(11);
        student1.setName("pkdsss");
        student1.setScore(3.1);
        student1.setOlympicGamer(true);

        Student student2 = new Student();
        student2.setAge(122);
        student2.setId(112);
        student2.setName("pdsaafkds");
        student2.setScore(3.5);
        student2.setOlympicGamer(false);

        GroupDtoForBase group = new GroupDtoForBase();
        group.setId(12);
        group.setName("group");

        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        list.add(student2);


        GroupWithStudents s = new GroupWithStudents();
        s.setStudents(list);
        s.setIdGroup(group);

        ObjectMapper mapper  =new ObjectMapper();
        System.out.println(mapper.writeValueAsString(s));

        System.out.println("SELECT g.id AS group_id,\n" +
                "       g.name AS group_name,\n" +
                "       s.id AS student_id,\n" +
                "       s.name AS student_name,\n" +
                "       s.age AS student_age,\n" +
                "       s.score AS student_score,\n" +
                "       s.olympic_gamer AS student_olympic\n" +
                "FROM jdbc.cross_table AS ct\n" +
                "JOIN jdbc.groups AS g ON ct.id_group = g.id\n" +
                "JOIN jdbc.students AS s ON ct.id_student = s.id\n" +
                "WHERE g.id = ?;");
    }
}
