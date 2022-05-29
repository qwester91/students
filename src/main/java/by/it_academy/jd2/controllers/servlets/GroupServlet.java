package by.it_academy.jd2.controllers.servlets;

import by.it_academy.jd2.dto.GroupDtoForBase;
import by.it_academy.jd2.dto.Student;
import by.it_academy.jd2.service.GroupService;
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

@WebServlet(name = "GroupServlet", urlPatterns = "/groups")
public class GroupServlet extends HttpServlet {


    private ObjectMapper mapper = new ObjectMapper();
    private IService<GroupDtoForBase> service = GroupService.getInstance();

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
        GroupDtoForBase group = mapper.readValue(inputStream, GroupDtoForBase.class);
        service.setItem(group);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");


        ServletInputStream inputStream = req.getInputStream();
        GroupDtoForBase group = mapper.readValue(inputStream, GroupDtoForBase.class);
        service.updateItem(group);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");


        ServletInputStream inputStream = req.getInputStream();
        GroupDtoForBase group = mapper.readValue(inputStream, GroupDtoForBase.class);
        service.deleteItem(group.getId());
    }

}
