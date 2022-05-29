package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.GroupDao;
import by.it_academy.jd2.dao.api.IDao;
import by.it_academy.jd2.dto.GroupDtoForBase;
import by.it_academy.jd2.service.api.IService;

import java.util.List;

public class GroupService implements IService<GroupDtoForBase> {
    private static final GroupService instance = new GroupService();
    private IDao<GroupDtoForBase> dao = GroupDao.getInstance();

    private GroupService() {

    }

    @Override
    public List<GroupDtoForBase> getListOfItem() {
        return dao.getListOfItem();
    }

    @Override
    public GroupDtoForBase getItem(int id) {
        return dao.getItem(id);
    }

    @Override
    public void setItem(GroupDtoForBase item) {
        dao.setItem(item);

    }

    @Override
    public void deleteItem(int id) {
        dao.deleteItem(id);

    }

    @Override
    public void updateItem(GroupDtoForBase item) {
        dao.updateItem(item);

    }
    public static GroupService getInstance(){
        return instance;
    }
}
