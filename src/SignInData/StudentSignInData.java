package SignInData;

import ShowData.StudentDataShow;
import com.signin.StudentSignIn;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StudentSignInData extends JFrame {
    JPanel leftPanel, rightPanel;
    JTextField studentEmail,studentId;
    JPasswordField studentPassword;
    //font object create
    Font font = new Font("Arial",Font.BOLD,17);
    private static int yPosition = 100;


    public StudentSignInData() {
        setTitle("Student sign In");
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setDefaultCloseOperation(StudentSignInData.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        leftPanel = new JPanel();
        leftPanel.setBounds(0,0,300,600);
        leftPanel.setLayout(null);
        add(leftPanel);



        rightPanel = new JPanel();
        rightPanel.setBounds(300,0,500,600);
        rightPanel.setLayout(null);
        add(rightPanel);


        JLabel loginLable = new JLabel("STUDENT LOGIN");
        loginLable.setBounds(130,100,250,40);
        loginLable.setFont(new Font("Arial",Font.BOLD,27));
        rightPanel.add(loginLable);

        int yPosotion = 180;


        //create id lable
        rightPanel.add(createLabel("Student ID:",yPosotion));
        studentId = new JTextField();
        studentId.setBounds(200,yPosotion,190,35);
        rightPanel.add(studentId);
        yPosotion+=50;

        //create email lable
        rightPanel.add(createLabel("Email add:",yPosotion));
        studentEmail = new JTextField();
        studentEmail.setBounds(200,yPosotion,190,35);
        rightPanel.add(studentEmail);
        yPosotion+=50;


        //create password lable
        rightPanel.add(createLabel("Password:",yPosotion));
        studentPassword = new JPasswordField();
        studentPassword.setBounds(200,yPosotion,190,35);
        rightPanel.add(studentPassword);
        yPosotion+=50;

        //create sign in button
        JButton loginButton = new JButton("Sign In");
        loginButton.setBounds(220,yPosotion,150,35);
        loginButton.setFont(new Font("Arial",Font.PLAIN,22));
        rightPanel.add(loginButton);





        //add actionListener to the sign in button

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentLoginRequest();
            }
        });


        ImageIcon leftIcon = new ImageIcon("icon/login_left.jpg");
        Image leftImage = leftIcon.getImage().getScaledInstance(300,600,Image.SCALE_SMOOTH);
        ImageIcon left = new ImageIcon(leftImage);
        JLabel leftLable = new JLabel(left);
        leftLable.setBounds(0,0,300,600);
        leftPanel.add(leftLable);

        ImageIcon rightIcon = new ImageIcon("icon/login_bg.jpg");
        Image rightImage = rightIcon.getImage().getScaledInstance(500,600,Image.SCALE_SMOOTH);
        ImageIcon right = new ImageIcon(rightImage);
        JLabel rightLable = new JLabel(right);
        rightLable.setBounds(0,0,500,600);

        rightPanel.add(rightLable);
        leftPanel.add(leftLable);


        setVisible(true);
    }
    public JLabel createLabel(String text, int yPosition) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(font);
        label.setBounds(90, yPosition, 170, 25);
        return label;
    }


    //student login request method

    private void studentLoginRequest(){

        String id = studentId.getText();
        String email = studentEmail.getText();
        String password = studentPassword.getText();
        Integer x = Integer.parseInt(id);
        if (!id.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (isInt(id)){
                // passing data to  login class
                StudentSignIn studentSignIn = new StudentSignIn(x,email,password);
                if (studentSignIn.checkValidation()){
                    new StudentDataShow(x);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"login failed");
                }
            }else {
                JOptionPane.showMessageDialog(null,"ID must be Integer","Warning",JOptionPane.WARNING_MESSAGE);
            }

        }else {
            JOptionPane.showMessageDialog(null,"Please fill all field","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    public boolean isInt(String input) {
        return input.matches("[0-9]+");
    }
    public static void main(String[] args) {
        new StudentSignInData();
    }
}
