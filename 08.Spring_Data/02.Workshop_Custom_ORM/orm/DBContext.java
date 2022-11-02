package orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DBContext<E> {

    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> entityClass) throws SQLException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException;

    Iterable<E> find(Class<E> entityClass, String where) throws SQLException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException;

    E findFirst(Class<E> entityClass) throws SQLException,
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException;

    E findFirst(Class<E> entityClass, String where) throws SQLException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException;

    void createTable(Class<E> entityClass) throws SQLException;

    void addColumns(Class<E> entityClass) throws SQLException;

    void delete(E entity) throws IllegalAccessException, SQLException;
}
