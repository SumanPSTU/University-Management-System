package com.signin;

import com.connection.ConnectionData;
import ShowData.TeacherDataShow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherSignIn {
    private int teacherId;
    private String email;
    private String password;
    public TeacherSignIn(int teacherId,String  email,String password){
        this.teacherId = teacherId;
        this.email = email;
        this.password = password;

    }

    public boolean checkValidation(){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.teacher_info where email = ? and password = ?";
            PreparedStatement preparedStatement = connection.con.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,connection.hashPassword(password));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                connection.close();
                return true;
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public int getTeacherId() {
        return teacherId;
    }
}