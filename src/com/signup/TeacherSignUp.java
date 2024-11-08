package com.signup;


import com.connection.ConnectionData;
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
            String query = "INSERT INTO teacher_info (teacher_id, name, faculty, email, phone_no, date_of_birth, department, present_add, permanent_add, password,image) VALUES ('"+teacherID+"'," +
                    "'"+teacherName+"', '"+faculty+"', '"+email+"', '"+mobileNo+"', '"+dateOfBirth+"', '"+department+"', '"+presentAdd+"', '"+permanentAdd+"','"+password+"','"+imagePath+"')";
            connectionData.pst.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
