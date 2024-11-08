package com.signup;

import com.connection.ConnectionData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentSignUp {
    private int studentID;
    private int studentReg;
    private String studentName;
    private String fathersName;
    private String mothersName;
    private String mobileNo;
    private String email;
    private String password;
    private String gender;
    private String permanentAdd;
    private String presentAdd;
    private String dateOfBirth;
    private String faculty;
    private String imagePath;
    private String semister;

    public StudentSignUp(int studentID, int studentReg, String studentName, String fathersName, String mothersName, String mobileNo, String email, String password,String gender, String permanentAdd, String presentAdd, String dateOfBirth, String faculty,String imagePath,String semister) {
        this.studentID = studentID;
        this.studentReg = studentReg;
        this.studentName = studentName;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.permanentAdd = permanentAdd;
        this.presentAdd = presentAdd;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.imagePath = imagePath;
        this.semister = semister;


        insertStudent();

    }

    private void insertStudent() {
        ConnectionData connectionData = new ConnectionData();
        try {
            String query = "INSERT INTO student_info (id, regi_no, name, father_name, mother_name, mobile_no, email, permanent_add, present_add, date_of_birth, faculty, password, gender,image,semister) " +
                    "VALUES ('"+studentID+"','"+studentReg+"','"+studentName+"','"+fathersName+"','"+mothersName+"','"+mobileNo+"','"+email+"','"+permanentAdd+"','"+presentAdd+"','"+dateOfBirth+"','"+faculty+"','"+connectionData.hashPassword(password)+"','"+gender+"','"+imagePath+"','"+semister+"')";
            connectionData.pst.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
