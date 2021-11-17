package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class AbstractDAO creates general queries on the database: SELECT, INSERT, UPDATE, DELETE
 * @param <T> represents a generic type
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    public final Class<T> type;

    /**
     * This method represents the constructor of the AbstractDAO class and gets the name of the provided class T
     *
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * This method creates the objects returned by the result set obtained from executing a query
     * @param resultSet represents the result set of the statement
     * @return a list of objects
     */
    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = null;
                try {
                    instance = type.getDeclaredConstructor().newInstance();
                } catch (InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = null;
                    try {
                        propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    }
                    assert propertyDescriptor != null;
                    Method method = propertyDescriptor.getWriteMethod();
                    try {
                        method.invoke(instance, value);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method creates a string representing a SELECT query
     * @param field represents the field by which the selection is made
     * @return a string representing the SELECT query
     */
    private String createFindByIdQuery(String field) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " =?");
        return stringBuilder.toString();
    }


    /**
     * This method executes a SELECT query
     * @param id represents the ID by which the selection is made
     * @return the first element of the list returned by the createObjects method
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindByIdQuery("ID");
        try {
            connection = ConnectionFactory.getConnection();
            try {
                statement = connection.prepareStatement(query);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            assert statement != null;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * This method creates a string representing an INSERT query
     * @param t represents the object which will be inserted
     * @return a string representing the INSERT query
     */
    private String createInsertQuery(T t) {
        boolean isFirst = true;
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(type.getSimpleName()).append("(");
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("ID")) {
                if (isFirst) {
                    stringBuilder.append(field.getName());
                    isFirst = false;
                } else {
                    stringBuilder.append(",").append(field.getName());
                }
                counter++;
            }
        }
        stringBuilder.append(") VALUES(");
        isFirst = true;
        for (int index = 1; index <= counter; index++) {
            if (isFirst) {
                stringBuilder.append("?");
                isFirst = false;
            } else {
                stringBuilder.append(",?");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * This method executes an INSERT query
     * @param t represents the object which will be inserted
     * @return true if the insert was successfully executed, false otherwise
     */
    public boolean insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int counter = 1;
            for (Field field : type.getDeclaredFields()) {
                if (!field.getName().equals("ID")) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object writeData = method.invoke(t);
                    statement.setObject(counter, writeData);
                    counter++;
                }
            }
            if (statement.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert: " + e.getMessage());
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
        return true;
    }

    /**
     * This method creates a string representing an UPDATE query
     * @return a string representing the UPDATE query
     */
    private String createUpdateQuery() {
        boolean isFirst = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("ID")) {
                if (isFirst) {
                    stringBuilder.append(field.getName()).append("=?");
                    isFirst = false;
                } else {
                    stringBuilder.append(",").append(field.getName()).append("=?");
                }
            }
        }
        stringBuilder.append(" WHERE ID=?");
        return stringBuilder.toString();
    }

    /**
     * This method executes an UPDATE query
     * @param t represents the object which will be updated
     * @return true if the update was successfully executed, false otherwise
     */
    public boolean update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int counter = 1;
            int valueOfID = 0;
            for (Field field : type.getDeclaredFields()) {
                if (!field.getName().equals("ID")) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object writeData = method.invoke(t);
                    statement.setObject(counter, writeData);
                    counter++;
                }
                else{
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object writeData = method.invoke(t);
                    valueOfID = (int) writeData;
                }
            }
            statement.setInt(counter,valueOfID);
            if (statement.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update: " + e.getMessage());
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
        return true;
    }

    /**
     * This method creates a DELETE query
     * @return a string representing the DELETE query
     */
    private String createDeleteQuery() {
        boolean isFirst = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE ID=?");
        return stringBuilder.toString();
    }

    /**
     * This method executes a DELETE query
     * @param t represents the object which will be deleted
     * @return true if the delete was successfully executes, false otherwise
     */
    public boolean delete(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            for (Field field : type.getDeclaredFields()) {
                if (field.getName().equals("ID")) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object writeData = method.invoke(t);
                    statement.setInt(1, (Integer) writeData);
                }

            }
            if (statement.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete: " + e.getMessage());
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
        return true;
    }

    /**
     * This method creates a SELECT ALL query
     * @return a string representing the SELECT ALL query
     */
    private String createFindAllQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(type.getSimpleName());
        return stringBuilder.toString();
    }

    /**
     * This method executes a SELECT ALL query
     * @return a list of objects returned by the execution of the query
     */
    public List<T> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                statement = connection.prepareStatement(query);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            assert statement != null;
            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
