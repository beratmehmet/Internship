package org.example.dao;

import org.example.model.Login;
import org.example.model.User;

import java.sql.SQLException;

public interface UserDao {

    void register(User user) throws SQLException;

    User validateUser(Login login);
}