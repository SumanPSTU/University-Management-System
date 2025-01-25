package library.management;

import javax.swing.*;
import java.sql.PreparedStatement;

public class UpdateBook {
    private String title;
    private String author;
    private int year;
    private int isbn;
    private int numOfCopies;

    public UpdateBook(String title, String author, int year, int isbn, int numOfCopies) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.numOfCopies = numOfCopies;
        updateBook();

    }
    public boolean updateBook() {
        return update();
    }

    private boolean update() {
        LibraryConn conn = new LibraryConn();
        String query = "UPDATE books SET title = ?, numofcopy = ?, author = ?, publishyear = ? WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = conn.con.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, numOfCopies);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, year);
            preparedStatement.setInt(5, isbn);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
