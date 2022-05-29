package by.it_academy.jd2.controllers.servlets;

import by.it_academy.jd2.dto.Student;
import by.it_academy.jd2.service.StudentService;
import by.it_academy.jd2.service.api.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {


    private ObjectMapper mapper = new ObjectMapper();
    private IService<Student> service = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.print(mapper.writeValueAsString(service.getListOfItem()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");


        ServletInputStream inputStream = req.getInputStream();
        Student student = mapper.readValue(inputStream, Student.class);
        service.setItem(student);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");


        ServletInputStream inputStream = req.getInputStream();
        Student student = mapper.readValue(inputStream, Student.class);
        service.updateItem(student);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");


        ServletInputStream inputStream = req.getInputStream();
        Student student = mapper.readValue(inputStream, Student.class);
        service.deleteItem(student.getId());    }

}
