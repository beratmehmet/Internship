package org.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.example.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.example.model.Login;
import org.example.model.User;

public class UserDaoImpl implements UserDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void register(User user) {

        String sql = "insert into users values(?,?,?,?,?,?)";

        String sql2 = "select * from users where username='" + user.getUsername()+ "'";

        List<User> users = jdbcTemplate.query(sql2, new UserMapper());

        if (users.isEmpty()){
            jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getEmail() , null, user.getSex(), user.isEnabled()});
        }else {
            throw new UserAlreadyExistException("Username already in use");
        }


    }

    public User validateUser(Login login) {

        String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
                + "'";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

}

class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setSex(rs.getInt("sex"));
        user.setEnabled(rs.getBoolean("enabled"));

        return user;
    }
}