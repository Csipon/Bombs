package project.service.mysql;

import project.service.entity.Product;
import project.service.entity.User;
import project.service.dao.DaoFactory;
import project.service.dao.GenericDao;
import project.service.dao.PersistException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HOUSE on 12.07.2016.
 */
public class MySqlDaoFactory implements DaoFactory<Connection> {

    private static final String NAME = "root";
    private static final String PASSWORD = "wsgf1996";
    private static final String URL = "jdbc:mysql://localhost:3306/oxeygen";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(DRIVER);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(User.class, new DaoFactory.DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Product.class, new DaoFactory.DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlProductDao(MySqlDaoFactory.this, connection);
            }
        });
    }
}
