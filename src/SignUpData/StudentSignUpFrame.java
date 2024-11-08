package SignUpData;

import com.connection.ConnectionData;
import com.signup.StudentSignUp;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSignUpFrame extends JFrame {
    private JPanel leftPanel, rightPanel;
    JTextField studentID, studentReg, studentName, fathersName, mothersName, mobileNo, email;
    JPasswordField password;
    JRadioButton male, female;
    ButtonGroup genderGroup;
    JTextField permanentAdd, presentAdd;
    JDateChooser dateOfBirth;
    JTextField faculty;
    JButton submitButton, cancle,uploadButton;
    JLabel ImageLable;
    ImageIcon image;
    File selectedFile;
    Path sourcePath = null;
    JComboBox comboBox;

    private Font font = new Font("Arial", Font.PLAIN, 18);
    private String[] semester = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth"};

    public StudentSignUpFrame() {
        setTitle("Student Sign Up Form");
        setSize(1000, 750);
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 300, 750);
        leftPanel.setLayout(null);
        // Right Panel
        rightPanel = new JPanel();
        rightPanel.setBounds(300, 0, 700, 750);
        rightPanel.setLayout(null);

        // upload image

        image = new ImageIcon("image/head.png");
        Image img = image.getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH);
        ImageIcon image1 = new ImageIcon(img);
        ImageLable = new JLabel(image1);
        ImageLable.setBounds(500,100,150,180);
        rightPanel.add(ImageLable);



        // University logo
        ImageIcon pstuLogo = new ImageIcon("icon/pstu_logo.png");
        Image logo = pstuLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logo);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(80, 20, 100, 100);
        leftPanel.add(logoLabel);
        add(leftPanel);



        // Welcome and Form Labels
        JLabel welcomeLabel = new JLabel("Patuakhali Science & Technology University");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.lightGray);
        welcomeLabel.setBounds(5, 20, 680, 30);

        JLabel studentRegisterLabel = new JLabel("STUDENT REGISTER FORM");
        studentRegisterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        studentRegisterLabel.setForeground(Color.lightGray);
        studentRegisterLabel.setBounds(160, 60, 680, 23);

        rightPanel.add(welcomeLabel);
        rightPanel.add(studentRegisterLabel);

        // Form Fields
        int yPosition = 100;

        // Student ID
        rightPanel.add(createLabel("Student ID:", yPosition));
        studentID = new JTextField();
        studentID.setFont(font);
        studentID.setBounds(250, yPosition, 200, 30);
        rightPanel.add(studentID);
        yPosition += 40;

        // Registration Number
        rightPanel.add(createLabel("Registration No:", yPosition));
        studentReg = new JTextField();
        studentReg.setFont(font);
        studentReg.setBounds(250, yPosition, 200, 30);
        rightPanel.add(studentReg);
        yPosition += 40;

        // Student Name
        rightPanel.add(createLabel("Student Name:", yPosition));
        studentName = new JTextField();
        studentName.setFont(font);
        studentName.setBounds(250, yPosition, 200, 30);
        rightPanel.add(studentName);
        yPosition += 40;

        // Father's Name
        rightPanel.add(createLabel("Father's Name:", yPosition));
        fathersName = new JTextField();
        fathersName.setFont(font);
        fathersName.setBounds(250, yPosition, 200, 30);
        rightPanel.add(fathersName);
        yPosition += 40;

        // Mother's Name
        rightPanel.add(createLabel("Mother's Name:", yPosition));
        mothersName = new JTextField();
        mothersName.setFont(font);
        mothersName.setBounds(250, yPosition, 200, 30);
        rightPanel.add(mothersName);
        yPosition += 40;

        // Mobile Number
        rightPanel.add(createLabel("Mobile No:", yPosition));
        mobileNo = new JTextField();
        mobileNo.setFont(font);
        mobileNo.setBounds(250, yPosition, 200, 30);
        rightPanel.add(mobileNo);
        yPosition += 40;

        // Email
        rightPanel.add(createLabel("Email:", yPosition));
        email = new JTextField();
        email.setFont(font);
        email.setBounds(250, yPosition, 200, 30);
        rightPanel.add(email);
        yPosition += 40;

        // Password
        rightPanel.add(createLabel("Password:", yPosition));
        password = new JPasswordField();
        password.setFont(new Font("Arial", Font.BOLD, 26));
        password.setBounds(250, yPosition, 200, 30);
        rightPanel.add(password);
        yPosition += 40;

        // Gender
        rightPanel.add(createLabel("Gender:", yPosition));
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        male.setBounds(250, yPosition, 80, 30);
        female.setBounds(369, yPosition, 80, 30);


        rightPanel.add(male);
        rightPanel.add(female);
        yPosition += 40;

        // Date of Birth
        rightPanel.add(createLabel("Date of Birth:", yPosition));
        dateOfBirth = new JDateChooser();
        dateOfBirth.setFont(font);
        dateOfBirth.setBounds(250, yPosition, 200, 30);
        rightPanel.add(dateOfBirth);
        yPosition += 40;

        // Permanent Address
        rightPanel.add(createLabel("Permanent Address:", yPosition));
        permanentAdd = new JTextField();
        permanentAdd.setFont(font);
        permanentAdd.setBounds(250, yPosition, 200, 30);
        rightPanel.add(permanentAdd);
        yPosition += 40;

        // Present Address
        rightPanel.add(createLabel("Present Address:", yPosition));
        presentAdd = new JTextField();
        presentAdd.setFont(font);
        presentAdd.setBounds(250, yPosition, 200, 30);
        rightPanel.add(presentAdd);
        yPosition += 40;

        // Faculty
        rightPanel.add(createLabel("Faculty:", yPosition));
        faculty = new JTextField();
        faculty.setFont(font);
        faculty.setBounds(250, yPosition, 200, 30);
        rightPanel.add(faculty);
        yPosition += 40;
        rightPanel.add(createLabel("Semister",yPosition));

        comboBox = new JComboBox(semester);
        comboBox.setFont(font);
        comboBox.setBounds(250, yPosition, 200, 30);
        rightPanel.add(comboBox);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(350, yPosition+40, 110, 31);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 22));
        rightPanel.add(submitButton);

       /* // cancle button
        cancle = new JButton("Back");
        cancle.setBounds(420, yPosition+20, 110, 33);
        cancle.setFont(new Font("Arial", Font.PLAIN, 22));
        rightPanel.add(cancle);
*/
        uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(470, 310, 200, 40);
        uploadButton.setFont(new Font("Arial", Font.PLAIN, 22));
        rightPanel.add(uploadButton);


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                //specify selected file is an image file by using filter
                FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter( "Image Files", "jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp");

                fileChooser.setFileFilter(extensionFilter);


                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    sourcePath = selectedFile.toPath();

                    // Create the target
                    Path targetPath = Path.of("C:\\Users\\Sumon\\Unniversity Project\\Univarsity Management System\\image", selectedFile.getName());

                    try {
                        // Ensure the target directory exists
                        Files.createDirectories(targetPath.getParent());

                        // Copy the file to the target location
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                        // Display the image in the JLabel
                        ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image img = icon.getImage().getScaledInstance(150, 180, Image.SCALE_AREA_AVERAGING);
                        image.setImage(img);
                        ImageLable.setIcon(new ImageIcon(img));

                    } catch (IOException ex) {
                        System.err.println("File copy failed: " + ex.getMessage());
                    }
                }
            }
        });
        //add actionListener to the cancle button
       /* cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });*/
        // add actionListener to the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDataToDataBase();
            }
        });

        ImageIcon left = new ImageIcon("icon/left.jpg");
        Image leftImage = left.getImage().getScaledInstance(300, 750, Image.SCALE_SMOOTH);
        ImageIcon leftIcon = new ImageIcon(leftImage);
        JLabel lblLeft = new JLabel(leftIcon);
        lblLeft.setBounds(0, 0, 300, 750);
        leftPanel.add(lblLeft);

        // Background Image
        ImageIcon icon = new ImageIcon("icon/background.jpg");
        Image image = icon.getImage().getScaledInstance(700, 750, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(image);
        JLabel label = new JLabel(icon1);
        label.setBounds(0, 0, 700, 750);
        rightPanel.add(label);

        add(rightPanel);
        setVisible(true);
    }

    public void insertDataToDataBase() {
        String studentId = studentID.getText();
        String studentRegi = studentReg.getText();
        String student = studentName.getText();
        String fName = fathersName.getText();
        String mName = mothersName.getText();
        String mobNo = mobileNo.getText();
        String emailAdd = email.getText();
        String passworD = new String(password.getPassword());
        String gender;
        if (male.isSelected()) {
            gender = male.getText();
        } else if (female.isSelected()) {
            gender = female.getText();
        } else {
            gender = "";
        }
        String com = comboBox.getSelectedItem().toString();
        String perAdd = permanentAdd.getText();
        String preAdd = presentAdd.getText();
        String dob = ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText();
        String facultyInfo = faculty.getText();
        String source = (sourcePath == null) ? "" : sourcePath.toString();

        // Check if fields are not empty
        if (!student.isEmpty() && !studentId.isEmpty() && !fName.isEmpty() && !mName.isEmpty() && !mobNo.isEmpty()
                && !emailAdd.isEmpty() && !passworD.isEmpty() && !gender.isEmpty() && !perAdd.isEmpty()
                && !preAdd.isEmpty() && !dob.isEmpty() && !facultyInfo.isEmpty() && !com.isEmpty()) {

            // Validate if email is valid
            if (new ConnectionData().isValidEmail(emailAdd)) {

                // Validate password length
                if (passworD.length() >= 6) {

                    // Validate mobile number
                    if (isValidMobile(mobNo)) {

                        // Validate image selection
                        if (!source.isEmpty()) {

                            // Check if student ID is an integer
                            if (isInt(studentId)) {
                                int id = Integer.parseInt(studentId);

                                // Check if student registration number is an integer
                                if (isInt(studentRegi)) {
                                    int reg = Integer.parseInt(studentRegi);

                                    // Check if ID already exists
                                    if (!ifIdExists(id)) {

                                        // Check if email already exists
                                        if (!ifEmailExists(emailAdd)) {

                                            // Check if registration number already exists
                                            if (!ifRegiExists(reg)) {

                                                // Confirm with the user
                                                int option = JOptionPane.showConfirmDialog(null, "Are you sure?");
                                                if (option == JOptionPane.YES_OPTION) {
                                                    new StudentSignUp(id, reg, student, fName, mName, mobNo, emailAdd, passworD, gender, perAdd, preAdd, dob, facultyInfo, selectedFile.getName(),com);
                                                    JOptionPane.showMessageDialog(null, "Student Successfully Added");
                                                    complete();
                                                }

                                            } else {
                                                JOptionPane.showMessageDialog(null, "Registration no already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Email address already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Student Registration number must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Student ID must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Please select an image.", "Warning", JOptionPane.WARNING_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid mobile number (10 digits).", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Password should be at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean isValidMobile(String mobile) {
        return mobile.matches("^01\\d{9}$");
    }

    public void complete(){
        studentID.setText("");
        studentReg.setText("");
        studentName.setText("");
        fathersName.setText("");
        mothersName.setText("");
        mobileNo.setText("");
        email.setText("");
        password.setText("");
        dateOfBirth.getDateEditor().setDate(null);
        faculty.setText("");
        permanentAdd.setText("");
        presentAdd.setText("");
        sourcePath = null;
    }
    private JLabel createLabel(String text, int yPosition) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.lightGray);
        label.setFont(font);
        label.setBounds(50, yPosition, 170, 25);
        return label;
    }

    private boolean ifIdExists(int id){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.student_info where id = ?";
            PreparedStatement statement = connection.con.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //check email is exist or not
    private boolean ifEmailExists(String email){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.student_info where email = ?";
            PreparedStatement statement = connection.con.prepareStatement(query);
            statement.setString(1,email);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //check regi  is exist or not
    private boolean ifRegiExists(int regi){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.student_info where regi_no = ?";
            PreparedStatement statement = connection.con.prepareStatement(query);
            statement.setInt(1,regi);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isInt(String input) {
        return input.matches("[0-9]+");
    }


    public static void main(String[] args){
        new StudentSignUpFrame();
    }
}
