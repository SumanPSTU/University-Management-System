package library.management;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LibraryConn {

    public Connection con;
    public Statement pst;
    private final String url = "jdbc:mysql://localhost:3306/library";
    private final String user = "root";
    private final String password = "root@pass";

    public LibraryConn() {
        try {
            con = DriverManager.getConnection(url, user, password);
            pst = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean ifIsbnExist(int isbn) {
        String sql = "select * from library.books where isbn = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
