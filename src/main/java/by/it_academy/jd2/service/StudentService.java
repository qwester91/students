package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.StudentDao;
import by.it_academy.jd2.dao.api.IDao;
import by.it_academy.jd2.dto.Student;
import by.it_academy.jd2.service.api.IService;

import java.util.List;

public class StudentService implements IService<Student> {
    private static final StudentService instance = new StudentService();
    private IDao<Student> dao = StudentDao.getInstance();

    private StudentService() {

    }

    @Override
    public List<Student> getListOfItem() {
        return dao.getListOfItem();
    }

    @Override
    public Student getItem(int id) {
        return dao.getItem(id);
    }

    @Override
    public void setItem(Student item) {
        dao.setItem(item);

    }

    @Override
    public void deleteItem(int id) {
        dao.deleteItem(id);

    }

    @Override
    public void updateItem(Student item) {
        dao.updateItem(item);

    }
    public static StudentService getInstance(){
        return instance;
    }
}
