package com.signin;
import com.connection.ConnectionData;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibrarianSignIn {
    private String email;
    private String password;
    public LibrarianSignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean signIn() {
        final String query = "SELECT * FROM librarian WHERE email = ? AND password = ?";
        ConnectionData connection = new ConnectionData();
        try {
            PreparedStatement preparedStatement = connection.con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, connection.hashPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                connection.close();
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

        return false;
    }
}
