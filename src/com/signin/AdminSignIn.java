package com.signin;

import Main.MainView;
import com.connection.ConnectionData;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminSignIn {
    private String email;
    private String password;

    public AdminSignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }
    private boolean checkAdmin(String email, String password) {
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.admin where email = ? and password = ?";
            PreparedStatement pre = connection.con.prepareStatement(query);
            pre.setString(1, email);
            pre.setString(2, connection.hashPassword(password));
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean signIn() {
        return checkAdmin(email, password);
    }
}
