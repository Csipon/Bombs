package project.service.mysql;

import project.service.entity.User;
import project.service.dao.AbstractJDBCDao;
import project.service.dao.DaoFactory;
import project.service.dao.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOUSE on 12.07.2016.
 */

public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {

    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, nick, first_name, last_name, password, about, email FROM oxeygen.users";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE oxeygen.users SET nick = ?, first_name = ?, last_name = ?, password = ?, about = ?, email = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM oxeygen.users WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO oxeygen.users (nick, first_name, last_name, password, about, email) VALUES(?, ?, ?, ?, ?, ?)";
    }

    @Override
    public User create() throws PersistException {
        User user = new User();
        return persist(user);
    }

    public MySqlUserDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistUser user = new PersistUser();
                user.setId(rs.getInt("id"));
                user.setNick(rs.getString("nick"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPassword(rs.getString("password"));
                user.setAbout(rs.getString("about"));
                user.setEmail(rs.getString("email"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getNick());
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setString(4, object.getPassword());
            statement.setString(5, object.getAbout());
            statement.setString(6, object.getEmail());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getNick());
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setString(4, object.getPassword());
            statement.setString(5, object.getAbout());
            statement.setString(6, object.getEmail());
            statement.setInt(7, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}