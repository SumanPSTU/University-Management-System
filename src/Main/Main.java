package Main;

import com.connection.ConnectionData;
import com.recover.RecoverAdmin;
import com.recover.RecoverPass;
import com.signin.AdminSignIn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame implements ActionListener {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JLabel clickHere;

    public Main() {
        setTitle("Admin sign in");
        setSize(500,350);
        setIconImage(new ImageIcon("icon/user.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);


        //main prompt

        JLabel main = new JLabel(" Admin sign in");
        main.setSize(200,30);
        main.setLocation(160,40);
        main.setFont(new Font("Arial",Font.BOLD,25));
        add(main);

        JLabel email = new JLabel("Enter email");
        email.setSize(200,30);
        email.setLocation(70,100);
        email.setFont(new Font("Arial",Font.PLAIN,17));
        add(email);

        emailField = new JTextField();
        emailField.setSize(200,30);
        emailField.setLocation(200,100);
        emailField.setBorder(new EmptyBorder(0,5,0,5));
        emailField.setFont(new Font("Arial",Font.PLAIN,16));
        add(emailField);



        JLabel pass = new JLabel("Enter password");
        pass.setSize(200,30);
        pass.setLocation(70,160);
        pass.setFont(new Font("Arial",Font.PLAIN,16));
        add(pass);

        passwordField = new JPasswordField();
        passwordField.setSize(200,30);
        passwordField.setBorder(new EmptyBorder(0,5,0,5));
        passwordField.setLocation(200,160);
        passwordField.setFont(new Font("Arial",Font.PLAIN,25));
        add(passwordField);


        signInButton = new JButton("Sign in");
        signInButton.setSize(100,30);
        signInButton.setLocation(240,210);
        signInButton.setFont(new Font("Arial",Font.PLAIN,17));
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(this);
        add(signInButton);

        JLabel forgetPass = new JLabel("Forget password?");
        forgetPass.setBounds(150,240,150,40);
        forgetPass.setFont(new Font("Arial",Font.PLAIN,17));
        add(forgetPass);

        clickHere = new JLabel("<html><u>Click here</u></html>");
        clickHere.setBounds(290,240,150,40);
        clickHere.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clickHere.setForeground(Color.BLUE);
        clickHere.setFont(new Font("Arial",Font.PLAIN,17));

        // add mouse listener to click here lable
        clickHere.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               recoverPassword();
            }
        });
        add(clickHere);


        setForeground(new Color(168, 168, 158));
        setVisible(true);
    }


    //dialog for recovery password
    public void recoverPassword() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Recover password");
        dialog.setIconImage(new ImageIcon("icon/pass.png").getImage());
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(null);

        JLabel email = new JLabel("Enter email");
        email.setBounds(45,15,200,40);
        email.setFont(new Font("Arial",Font.PLAIN,17));
        dialog.add(email);

        JTextField emailRec = new JTextField();
        emailRec.setBounds(45,60,200,30);
        emailRec.setFont(new Font("Arial",Font.PLAIN,17));
        dialog.add(emailRec);

        JButton recButton = new JButton("Check email");
        recButton.setBounds(45,95,135,27);
        recButton.setFont(new Font("Arial",Font.PLAIN,17));
        dialog.add(recButton);
        recButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailRec.getText();
                if(!email.isEmpty()) {
                    if(new ConnectionData().isValidEmail(email)) {
                        if (new RecoverPass(email).recover()) {
                            new RecoverAdmin(email,new RecoverPass().generateCaptcha());
                            dialog.dispose();
                        }else {
                            JOptionPane.showMessageDialog(null,"Email does not exist");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "enter valid email address");
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Please enter email");
                }
            }
        });

        dialog.setVisible(true);
    }


    //action listener for sing in button
    @Override
    public void actionPerformed(ActionEvent e) {

        ConnectionData connection = new ConnectionData();
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if(e.getSource() == signInButton) {
            if (!email.isEmpty() && !password.isEmpty()) {
                if (connection.isValidEmail(email)) {
                    if (password.length() >= 6) {
                        AdminSignIn signIn = new AdminSignIn(email,password);
                        if (signIn.signIn()){
                            new MainView();
                            dispose();
                        }else {
                            JOptionPane.showMessageDialog(null,"Invalid email or password");
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "Password must be at least 6 characters");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid email address");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Please enter email and password");
            }
        }
    }
    public static void main(String[] args) {
        new Main();
    }

}