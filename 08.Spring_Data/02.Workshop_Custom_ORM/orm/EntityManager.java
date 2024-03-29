package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {

    // I am aware of the over-reliance on SQL injection techniques.
    // The following class is for practice purpose only.

    private final Connection connection;
    private final String dbName;

    public EntityManager(Connection connection, String dbName) {
        this.connection = connection;
        this.dbName = dbName;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {

        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object id = primaryKey.get(entity);

        if (id == null || (long) id <= 0) {
            return doInsert(entity, primaryKey);
        }

        return doUpdate(entity, primaryKey);

    }

    @Override
    public Iterable<E> find(Class<E> entityClass) throws SQLException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        return find(entityClass, null);
    }

    @Override
    public Iterable<E> find(Class<E> entityClass, String where) throws SQLException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {

        String tableName = getTableName(entityClass);

        String whereCondition = where == null ? "" : "WHERE " + where;

        String selectQuery = "SELECT * FROM " + tableName + " " + whereCondition + ";";

        ResultSet resultSet = connection.prepareStatement(selectQuery).executeQuery();

        List<E> entities = new ArrayList<>();

        E entity = createEntity(entityClass, resultSet);

        while (entity != null) {
            entities.add(entity);
            entity = createEntity(entityClass, resultSet);
        }

        return entities;
    }

    @Override
    public E findFirst(Class<E> entityClass)
            throws SQLException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        return findFirst(entityClass, null);
    }

    @Override
    public E findFirst(Class<E> entityClass, String where)
            throws SQLException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        String tableName = getTableName(entityClass);

        String whereCondition = where == null ? "" : "WHERE " + where + " ";

        String selectQuery = "SELECT * FROM " + tableName + " " + whereCondition + "LIMIT 1;";

        ResultSet resultSet = connection.prepareStatement(selectQuery).executeQuery();

        E entity = createEntity(entityClass, resultSet);

        if (entity == null) {
            throw new IllegalStateException("No results match criteria.");
        }

        return entity;
    }

    @Override
    public void createTable(Class<E> entityClass) throws SQLException {

        String tableName = getTableName(entityClass);

        String id = getId(entityClass).getName();

        String columns = String.join(", ", getFieldsFrom(entityClass));

        String createTableQuery = "CREATE TABLE " + tableName + " (" +
                id + " INT PRIMARY KEY AUTO_INCREMENT, " +
                columns + ");";

        connection.prepareStatement(createTableQuery).execute();
    }

    @Override
    public void addColumns(Class<E> entityClass) throws SQLException {

        String tableName = getTableName(entityClass);

        Set<String> existingColumns = getExistingColumns(entityClass, this.dbName, tableName);

        String newColumns = String.join(", ", getColumnsNotInTable(entityClass, existingColumns));

        String addTablesQuery = "ALTER TABLE " + tableName + " " + newColumns + ";";

        connection.prepareStatement(addTablesQuery).execute();
    }

    @Override
    public void delete(E entity) throws IllegalAccessException, SQLException {

        Class<?> entityClass = entity.getClass();

        String tableName = getTableName(entityClass);

        Field primaryKey = getId(entityClass);

        String primaryKeyName = primaryKey.getName();

        primaryKey.setAccessible(true);
        Object primaryKeyValue = primaryKey.get(entity);

        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + primaryKeyName + " = " + primaryKeyValue;

        connection.prepareStatement(deleteQuery).execute();
    }

    private List<String> getColumnsNotInTable(Class<E> entityClass, Set<String> existingColumns) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f ->
                        (f.isAnnotationPresent(Column.class)) &&
                                (!existingColumns.contains(f.getAnnotation(Column.class).name()))
                )
                .map(f -> "ADD COLUMN " + f.getAnnotation(Column.class).name() + " " + getFieldType(f))
                .collect(Collectors.toList());
    }

    private Set<String> getExistingColumns(Class<E> entityClass, String dbName, String tableName)
            throws SQLException {

        Set<String> existingColumns = new HashSet<>();

        String id = getId(entityClass).getName();

        String selectColumnsQuery = "SELECT COLUMN_NAME " +
                "FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_SCHEMA = " + "'" + dbName + "'" +
                " AND TABLE_NAME = " + "'" + tableName + "'" +
                " AND COLUMN_NAME != " + "'" + id + "'";

        ResultSet resultSet = connection.prepareStatement(selectColumnsQuery).executeQuery();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            existingColumns.add(columnName);
        }

        return existingColumns;
    }


    private List<String> getFieldsFrom(Class<E> entityClass) {
        List<String> fieldAndTheirTypes = new ArrayList<>();

        for (Field field : entityClass.getDeclaredFields()) {

            if (field.isAnnotationPresent(Column.class)) {
                String fieldName = field.getAnnotation(Column.class).name();
                String fieldType = getFieldType(field);

                fieldAndTheirTypes.add(fieldName + " " + fieldType);
            }

        }
        return fieldAndTheirTypes;
    }

    private String getFieldType(Field f) {
        Class<?> type = f.getType();

        if (type == int.class || type == Integer.class) {
            return "INT";
        } else if (type == LocalDate.class) {
            return "DATE";
        } else {
            return "VARCHAR(80)";
        }
    }


    private Field getId(Class<?> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity doesn't have id."));
    }

    private String getTableName(Class<?> entityClass) {
        Entity annotation = entityClass.getAnnotation(Entity.class);

        if (annotation == null) {
            throw new UnsupportedOperationException("Provided class does not have Entity annotation");
        }

        return annotation.name();
    }

    private List<String> getColumnValues(E entity) throws IllegalAccessException {
        List<String> values = new ArrayList<>();
        for (Field f : entity.getClass().getDeclaredFields()) {

            if (f.isAnnotationPresent(Column.class)) {

                f.setAccessible(true);
                Object value = f.get(entity);
                values.add("'" + value.toString() + "'");
            }
        }
        return values;
    }

    private List<String> getColumnNames(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.toList());
    }

    private E createEntity(Class<E> entityClass, ResultSet resultSet)
            throws InstantiationException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, SQLException {

        if (!resultSet.next()) {
            return null;
        }

        E entity = entityClass.getDeclaredConstructor().newInstance();

        fillEntity(entity, resultSet);

        return entity;
    }

    private void fillEntity(E entity, ResultSet resultSet)
            throws SQLException, IllegalAccessException {
        for (Field f : entity.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class) || f.isAnnotationPresent(Id.class)) {
                f.setAccessible(true);
                fillField(f, resultSet, entity);
            }
        }
    }

    private void fillField(Field f, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {

        Column annotation = f.getAnnotation(Column.class);
        String columnName = annotation == null ? f.getName() : annotation.name();

        if (f.getType() == long.class) { // id
            f.set(entity, resultSet.getLong(columnName));
        } else if (f.getType() == int.class) { // age
            f.set(entity, resultSet.getInt(columnName));
        } else if (f.getType() == LocalDate.class) { // registration
            f.set(entity, LocalDate.parse(resultSet.getString(columnName)));
        } else { // username
            f.set(entity, resultSet.getString(columnName));
        }
    }

    private boolean doInsert(E entity, Field primaryKey) throws IllegalAccessException, SQLException {

        String tableName = getTableName(entity.getClass());

        String columnNames = String.join(", ", getColumnNames(entity));

        String columnValues = String.join(", ", getColumnValues(entity));

        String insertQuery = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + columnValues + ");";

        boolean isInserted = connection.prepareStatement(insertQuery).execute();

        String retrieveLastEntityId = "SELECT " + primaryKey.getName() +
                " FROM " + tableName +
                " ORDER BY id DESC LIMIT 1;";
        ResultSet resultSet = connection.prepareStatement(retrieveLastEntityId).executeQuery();
        resultSet.next();
        long id = resultSet.getLong(primaryKey.getName());

        primaryKey.setAccessible(true);
        primaryKey.setLong(entity, id);

        return isInserted;
    }

    private boolean doUpdate(E entity, Field primaryKey) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());

        String columnUpdates = String.join(", ", getColumnUpdates(entity));

        primaryKey.setAccessible(true);
        long id = (long) primaryKey.get(entity);

        String updateQuery = "UPDATE " + tableName + " SET " + columnUpdates +
                " WHERE " + primaryKey.getName() + " = " + id;

        return connection.prepareStatement(updateQuery).execute();
    }

    private List<String> getColumnUpdates(E entity) throws IllegalAccessException {
        List<String> columnNames = getColumnNames(entity);
        List<String> columnValues = getColumnValues(entity);

        List<String> updates = new ArrayList<>();
        for (int i = 0; i < columnNames.size(); i++) {
            String update = columnNames.get(i) + " = " + columnValues.get(i);
            updates.add(update);
        }
        return updates;
    }
}
