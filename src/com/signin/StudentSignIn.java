package com.signin;


import com.connection.ConnectionData;
import com.signup.StudentSignUp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSignIn {
    private String email;
    private String password;
    private int studebtId;
    public StudentSignIn(int studentId,String email, String password) {
        this.studebtId = studentId;
        this.email = email;
        this.password = password;
    }

    public boolean checkValidation() {
        ConnectionData connection = new ConnectionData();

        try {
            String query = "select * from university.student_info where email = ? and password = ?";
            PreparedStatement preparedStatement = connection.con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, connection.hashPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public int getStudebtId(){
        return studebtId;
    }

}