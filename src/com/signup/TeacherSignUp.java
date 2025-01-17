package com.signup;

import com.connection.ConnectionData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherSignUp {
    private int teacherID;
    private String teacherName;
    private String mobileNo;
    private String email;
    private String password;
    private String gender;
    private String permanentAdd;
    private String presentAdd;
    private String dateOfBirth;
    private String faculty;
    private String department;
    private String imagePath;

    public TeacherSignUp(int teacherID, String teacherName, String mobileNo, String email, String password, String gender, String permanentAdd, String presentAdd, String dateOfBirth, String faculty,String department,String imagePath) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.permanentAdd = permanentAdd;
        this.presentAdd = presentAdd;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.department = department;
        this.imagePath = imagePath;

        //insert teacher data to the database
        insertTeacher();
    }


    //method for insert data into database
    private void insertTeacher() {
        ConnectionData connectionData = new ConnectionData();
        try {
            Connection conn = connectionData.getConnection();
            String query = "INSERT INTO teacher_info (teacher_id, name, faculty, email, phone_no, date_of_birth, department, present_add, permanent_add, password, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, teacherID);
            pst.setString(2, teacherName);
            pst.setString(3, faculty);
            pst.setString(4, email);
            pst.setString(5, mobileNo);
            pst.setString(6, dateOfBirth);
            pst.setString(7, department);
            pst.setString(8, presentAdd);
            pst.setString(9, permanentAdd);
            pst.setString(10, password);
            pst.setString(11, imagePath);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
