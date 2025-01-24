package library.management;


import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBook {
    private String title;
    private String author;
    private int year;
    private int isbn;
    private int numOfCopies;
    public AddBook(String title, String author, int year, int isbn, int numOfCopies) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.numOfCopies = numOfCopies;
        setData();

    }
    public void setData() {

        String query = "INSERT INTO books VALUES (?,?, ?,?,?)";

        try {
            LibraryConn conn = new LibraryConn();
            PreparedStatement preparedStatement = conn.con.prepareStatement(query);
            preparedStatement.setInt(1, isbn);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, numOfCopies);
            preparedStatement.setString(4, author);
            preparedStatement.setInt(5, year);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error inserting data");
        }
    }
}