package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IDao;
import by.it_academy.jd2.dto.GroupDtoForBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IDao<GroupDtoForBase> {
    private static final GroupDao instance = new GroupDao();

    private GroupDao(){

    }

    @Override
    public List<GroupDtoForBase> getListOfItem() {
        List<GroupDtoForBase> groupList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id,\n" +
                     "       name\n" +
                     "FROM jdbc.groups;")){


            while (resultSet.next()) {
                groupList.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groupList;
    }

    @Override
    public GroupDtoForBase getItem(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id,\n" +
                     "       name,\n" +
                     "FROM   jdbc.groups\n" +
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
    public void setItem(GroupDtoForBase item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO jdbc.groups(name)\n" +
                     "VALUES (?);")){


            statement.setString(1, item.getName());

            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItem(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM jdbc.groups\n" +
                     "        WHERE  id = ?;")){

            statement.setInt(1, id);
            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateItem(GroupDtoForBase item) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE jdbc.groups\n" +
                     "        SET name=?\n" +
                     "        WHERE id =?;")){


            statement.setString(1, item.getName());
            statement.setInt(2, item.getId());
            statement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private GroupDtoForBase map(ResultSet rs) throws SQLException {
        GroupDtoForBase group = new GroupDtoForBase();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
       return group;
    }

    public static GroupDao getInstance(){
        return instance;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
