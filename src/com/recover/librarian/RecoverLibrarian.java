package com.recover.librarian;

import com.connection.ConnectionData;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecoverLibrarian {
    private String email;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();


    public RecoverLibrarian(String email) {
        this.email = email;
    }
    public RecoverLibrarian() {

    }
    private boolean checkEmail(String email) {
        try {
            ConnectionData connection = new ConnectionData();
            String query = "select * from university.librarian where email = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                connection.close();
                return true;
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }



    public String generateCaptcha() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }
    public boolean recover() {
        return checkEmail(email);
    }
}
