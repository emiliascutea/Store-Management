package dao;

import connection.ConnectionFactory;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * The class ProductDAO inherits all methods from class AbstractDAO of generic type Product
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * This method represents the constructor of the ProductDAO class
     */
    public ProductDAO() {
        super();
    }

    /**
     * This method creates a string representing a SELECT query
     * @param field represents the field by which the selection is made
     * @return a string representing the SELECT query
     */
    private String createFindByNameQuery(String field) {
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
     * @param name represents the name by which the selection is made
     * @return the first element of the list returned by the createObjects method
     */
    public Product findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindByNameQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            try {
                statement = connection.prepareStatement(query);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            assert statement != null;
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}