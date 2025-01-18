package com.recover.librarian;

import com.connection.ConnectionData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetRecoverLibrarianPass {
    private String recoverPass;
    private String email;
    public SetRecoverLibrarianPass(String recoverPass, String email){
        this.recoverPass = recoverPass;
        this.email = email;
    }
    private boolean resetPassword(){
        try {
            ConnectionData connection = new ConnectionData();
            String query = "update university.admin set password = ? where email=?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, connection.hashPassword(recoverPass));
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return false;
    }
    public boolean successfulUpdate(){
        return resetPassword();
    }

}
