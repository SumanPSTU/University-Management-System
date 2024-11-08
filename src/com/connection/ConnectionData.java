package com.connection;

import java.security.MessageDigest;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionData {
    public Connection con;
    public Statement pst;
    private final String url = "jdbc:mysql://localhost:3306/university";
    private final String user = "root";
    private final String password = "root@pass";

    public ConnectionData() {
        try {
            con = DriverManager.getConnection(url, user, password);
            pst = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public String hashPassword(String password) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Consider using a logger
            }
        }
    }

    public boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
