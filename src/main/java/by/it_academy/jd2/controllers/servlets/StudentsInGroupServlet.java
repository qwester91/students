package by.it_academy.jd2.controllers.servlets;

import by.it_academy.jd2.dto.GroupWithStudents;
import by.it_academy.jd2.dto.Student;
import by.it_academy.jd2.service.StudentInGroupService;
import by.it_academy.jd2.service.StudentService;
import by.it_academy.jd2.service.api.IService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="StudentsInGroupServlet", urlPatterns = "/studentsgroup")
public class StudentsInGroupServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();
    private StudentInGroupService service = StudentInGroupService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        Integer idGroup = null;
        try {
            idGroup = Integer.parseInt(req.getParameter("id_droup"));
        }catch (IllegalArgumentException e){
            System.out.println("не верный id");
        }
        if (idGroup != null){
            GroupWithStudents group = service.getGroup(idGroup);
            String s = mapper.writeValueAsString(group);
            resp.getOutputStream().print(s);
        }else {
            System.out.println("не верный id");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupWithStudents groupWithStudents = mapper.readValue(req.getInputStream(), GroupWithStudents.class);
        service.setGroup(groupWithStudents);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupWithStudents groupWithStudents = mapper.readValue(req.getInputStream(), GroupWithStudents.class);
        service.deleteStudents(groupWithStudents);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        Integer idGroup = null;
        try {
            idGroup = Integer.parseInt(req.getParameter("id_droup"));
        }catch (IllegalArgumentException e){
            System.out.println("не верный id");
        }
        if (idGroup != null){
            service.deleteAllStudentsFromGroup(idGroup);
        }else {
            System.out.println("не верный id");
        }
    }
}
