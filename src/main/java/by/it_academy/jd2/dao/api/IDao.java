package by.it_academy.jd2.dao.api;

import java.util.List;

public interface IDao <T> extends AutoCloseable{
    /**
     * получить лист обьектов(всех что есть)
     * @return list<T>
     */
    List<T> getListOfItem();

    /**
     * получить один обьект
     * @param id обьекта
     * @return обьект T
     */
    T getItem(int id);

    /**
     * сохранить обьект
     * @param item обьект
     */
    void setItem(T item);

    /**
     * удалить обьект
     * @param id обьекта
     */
    void deleteItem(int id);

    /**
     * изменить обьект
     * @param item новый обьект
     */
    void updateItem(T item);
}
