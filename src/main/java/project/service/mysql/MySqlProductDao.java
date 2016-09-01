package project.service.mysql;

import org.springframework.context.annotation.Bean;
import project.service.entity.Product;
import project.service.dao.AbstractJDBCDao;
import project.service.dao.DaoFactory;
import project.service.dao.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOUSE on 30.07.2016.
 */
public class MySqlProductDao extends AbstractJDBCDao<Product, Integer> {
    private class PersistProduct extends Product {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, name, price, about  FROM oxeygen.product";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE oxeygen.product SET name = ?, price = ?, about = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM oxeygen.product WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO oxeygen.product (name, price, about) VALUES(?, ?, ?)";
    }

    @Override
    public Product create() throws PersistException {
        Product product = new Product();
        return persist(product);
    }

    public MySqlProductDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<Product> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Product> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistProduct product = new PersistProduct();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setAbout(rs.getString("about"));
                result.add(product);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Product object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setFloat(2, object.getPrice());
            statement.setString(3, object.getAbout());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Product object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setFloat(2, object.getPrice());
            statement.setString(3, object.getAbout());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
