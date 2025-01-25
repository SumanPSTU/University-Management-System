package library.management;

import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBook {
    private int isBn;
    public DeleteBook(int isBn) {
        this.isBn = isBn;
        deleteBook();
    }
    public boolean deleteData(){
        return deleteBook();
    }

    private boolean deleteBook() {
        LibraryConn conn = new LibraryConn();
        String query = "DELETE FROM books WHERE isbn= ?";
        try {
            PreparedStatement preparedStatement = conn.con.prepareStatement(query);
            preparedStatement.setInt(1, isBn);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
