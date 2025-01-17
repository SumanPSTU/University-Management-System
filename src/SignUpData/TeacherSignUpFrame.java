package SignUpData;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import com.connection.ConnectionData;
import com.signup.TeacherSignUp;
import com.toedter.calendar.JDateChooser;

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

public class TeacherSignUpFrame extends JFrame {
    private JPanel leftPanel, rightPanel;
    JTextField teacherID, teachernameName, mobileNo, email;
    JPasswordField password;
    JRadioButton male, female;
    ButtonGroup genderGroup;
    JTextField permanentAdd, presentAdd;
    JDateChooser dateOfBirth;
    JTextField faculty;
    JTextField department;
    JButton submitButton,uploadButton;
    ImageIcon image;
    JLabel ImageLable;
    Path imagePath = null;
    File selectedFile;
    private Font font = new Font("Arial", Font.PLAIN, 18);

    public TeacherSignUpFrame() {
        setTitle("Teacher Sign Up Form");
        setSize(1000, 750);
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setDefaultCloseOperation(TeacherSignUpFrame.EXIT_ON_CLOSE);
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

        // University logo
        ImageIcon pstuLogo = new ImageIcon("icon/pstu_logo.png");
        Image logo = pstuLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logo);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(80, 20, 100, 100);
        leftPanel.add(logoLabel);
        add(leftPanel);

        //upload image
        image = new ImageIcon("image/head.png");
        Image img = image.getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH);
        ImageIcon image1 = new ImageIcon(img);
        ImageLable = new JLabel(image1);
        ImageLable.setBounds(500,100,150,180);
        rightPanel.add(ImageLable);



        // Welcome and Form Labels
        JLabel welcomeLabel = new JLabel("Welcome to Patuakhali Science & Technology University");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel.setForeground(Color.lightGray);
        welcomeLabel.setBounds(5, 20, 680, 30);

        JLabel studentRegisterLabel = new JLabel("TEACHER REGISTER FORM");
        studentRegisterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        studentRegisterLabel.setForeground(Color.lightGray);
        studentRegisterLabel.setBounds(160, 60, 680, 23);

        rightPanel.add(welcomeLabel);
        rightPanel.add(studentRegisterLabel);

        // Form Fields
        int yPosition = 100;

        // Student ID
        JLabel login = new JLabel("STUDENT LOGIN");
        login.setFont(font);
        login.setBounds(100,100,170,40);

        rightPanel.add(createLabel("Teacher's ID:",yPosition));
        teacherID = new JTextField();
        teacherID.setFont(font);
        teacherID.setBounds(250, yPosition, 200, 30);
        rightPanel.add(teacherID);
        yPosition += 40;

        // Student Name
        rightPanel.add(createLabel("Teacher Name:", yPosition));
        teachernameName = new JTextField();
        teachernameName.setFont(font);
        teachernameName.setBounds(250, yPosition, 200, 30);
        rightPanel.add(teachernameName);
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
        dateOfBirth.setBounds(250, yPosition, 200, 30);
        rightPanel.add(dateOfBirth);
        yPosition += 40;

        // Permanent Address
        rightPanel.add(createLabel("Permanent Address:", yPosition));
        permanentAdd = new JTextField();
        permanentAdd.setBounds(250, yPosition, 200, 30);
        rightPanel.add(permanentAdd);
        yPosition += 40;

        // Present Address
        rightPanel.add(createLabel("Present Address:", yPosition));
        presentAdd = new JTextField();
        presentAdd.setBounds(250, yPosition, 200, 30);
        rightPanel.add(presentAdd);
        yPosition += 40;

        // Faculty
        rightPanel.add(createLabel("Faculty:", yPosition));
        faculty = new JTextField();
        faculty.setBounds(250, yPosition, 200, 30);
        rightPanel.add(faculty);
        yPosition += 40;

        rightPanel.add(createLabel("Department:", yPosition));
        department = new JTextField();
        department.setBounds(250, yPosition, 200, 30);
        rightPanel.add(department);
        yPosition += 40;

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(280, yPosition+20, 120, 33);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 22));
        rightPanel.add(submitButton);


        //upload button
        uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(470, 310, 200, 40);
        uploadButton.setFont(new Font("Arial", Font.PLAIN, 22));
        rightPanel.add(uploadButton);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                        "Image Files", "jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp");
                fileChooser.setFileFilter(imageFilter);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    imagePath = selectedFile.toPath();

                    //create targetPath

                    Path targetPath = Path.of("C:\\Users\\Sumon\\Unniversity Project\\Univarsity Management System\\image", selectedFile.getName());

                    try {
                        Files.createDirectories(targetPath.getParent());

                        Files.copy(imagePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                        ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image image2 = icon.getImage().getScaledInstance(150,180,Image.SCALE_SMOOTH);
                        image.setImage(image2);
                        ImageLable.setIcon(new ImageIcon(image2));

                    } catch (IOException ex) {
                       JOptionPane.showMessageDialog(null,"File not found");
                    }
                }
            }
        });

        // add actionListener to the submit button
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
        String teacherId = teacherID.getText();
        String teacherName = teachernameName.getText();
        String mobile = mobileNo.getText();
        String emailAdd = email.getText();
        String pass = password.getText();
        String gender;
        String permAdd = permanentAdd.getText();
        String presAdd = presentAdd.getText();
        String dob = ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText();
        String facultyInfo = faculty.getText();
        String departmentInfo = department.getText();
        if (male.isSelected()) {
            gender = male.getText();
        } else if (female.isSelected()) {
            gender = female.getText();
        } else {
            gender = "";
        }

        String selectFile = (imagePath == null)?"":selectedFile.getName().toString();

        // check all input are valid

        if (!teacherId.isEmpty() && !teacherName.isEmpty() && !mobile.isEmpty() && !emailAdd.isEmpty() && !pass.isEmpty() && !gender.isEmpty()
        && !permAdd.isEmpty() && !presAdd.isEmpty() && !dob.isEmpty() && !facultyInfo.isEmpty() && !departmentInfo.isEmpty()
        ) {
            if (new ConnectionData().isValidEmail(emailAdd)) {
                if (pass.length() >= 6) {
                    if (!selectFile.isEmpty()){
                        if (ifInt(teacherId)) {
                            if (!ifIdExists(Integer.parseInt(teacherId))) {
                                if (!ifEmailExists(emailAdd)){
                                    if (isValidMobile(mobile)){

                                        //pass all data to the  teacher sign up class

                                        new TeacherSignUp(Integer.parseInt(teacherId),teacherName,mobile,emailAdd,pass,gender,permAdd,presAdd,dob,facultyInfo,departmentInfo,selectFile);
                                        JOptionPane.showMessageDialog(null,"Successfully Insert Data");

                                    }else {
                                        JOptionPane.showMessageDialog(null,"Input valid Mobile Number");
                                    }
                                }else {
                                    JOptionPane.showMessageDialog(null,"Email Already Exists");
                                }
                            }else {
                                JOptionPane.showMessageDialog(null,"Teacher ID already Exist!","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"ID must be Integer!","Error",JOptionPane.ERROR_MESSAGE);
                        }

                    }else {
                        JOptionPane.showMessageDialog(null,"select an Image","Warning!",JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Password should be 6 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill all the field!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // create method for lable
    public JLabel createLabel(String text, int yPosition) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.lightGray);
        label.setFont(font);
        label.setBounds(50, yPosition, 170, 25);
        return label;
    }

    private boolean ifIdExists(int id){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.teacher_info where teacher_id = ?";
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

    private boolean ifInt(String id){
        return id.matches("[0-9]+");
    }
    //check email is exist or not
    private boolean ifEmailExists(String email){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.teacher_info where email = ?";
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
    public boolean isValidMobile(String mobile) {
        return mobile.matches("^01\\d{9}$");
    }

    public static void main(String[] args) {
        new TeacherSignUpFrame();
    }
}
