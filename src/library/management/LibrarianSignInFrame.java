package library.management;

import com.connection.ConnectionData;
import com.recover.librarian.RecoverLibrarian;
import com.recover.librarian.RecoverLibrarianPass;
import com.signin.LibrarianSignIn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibrarianSignInFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton;
    JLabel clickHere;

    public LibrarianSignInFrame() {
        setTitle("Librarian Sign In");
        setSize(500,400);
        setIconImage(new ImageIcon("icon/stackofbooks.png").getImage());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        JLabel heading = new JLabel("Librarian Sign In");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(Color.BLACK);
        heading.setBounds(150, 10, 300, 30);
        add(heading);

        JLabel label = new JLabel("Enter Email Address");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);
        label.setBounds(125,70,200,30);
        add(label);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setForeground(Color.BLACK);
        emailField.setBounds(125,100,250,36);
        emailField.setBorder(new EmptyBorder(0,5,0,5));
        add(emailField);

        JLabel label1 = new JLabel("Enter password");
        label1.setFont(new Font("Arial", Font.PLAIN, 18));
        label1.setForeground(Color.BLACK);
        label1.setBounds(125,140,200,30);
        add(label1);


        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBounds(125,180,250,36);
        passwordField.setBorder(new EmptyBorder(0,5,0,5));
        add(passwordField);

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.PLAIN, 18));
        signInButton.setForeground(Color.BLACK);
        signInButton.setBounds(170,240,160,40);
        add(signInButton);

        // add action listener to the sign-in button
        signInButton.addActionListener(e -> {
            librarianSignIn();
        });
        JLabel forgetPass = new JLabel("Forget password?");
        forgetPass.setBounds(150,280,150,40);
        forgetPass.setFont(new Font("Arial",Font.PLAIN,17));
        forgetPass.setForeground(Color.BLACK);
        add(forgetPass);

        clickHere = new JLabel("<html><u>Click here</u></html>");
        clickHere.setBounds(290,280,150,40);
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




        // set a background image
        ImageIcon icon = new ImageIcon("icon/book.jpg");
        Image image = icon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(image);
        JLabel backLabel = new JLabel(icon1);
        backLabel.setBounds(0, 0, 500, 400);
        add(backLabel);

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
                        if (new RecoverLibrarian(email).recover()) {

                            // pass email and generated captcha
                            new RecoverLibrarianPass(email,new RecoverLibrarian().generateCaptcha());
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
    private void librarianSignIn() {
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());

        //check all validity
        if (!email.isEmpty() && !password.isEmpty()) {
            if (isValidEmail(email)) {
                if (password.length()>=6){
                    // pass data to the sign in class
                    LibrarianSignIn signIn = new LibrarianSignIn(email,password);
                    if (signIn.signIn()){
                        this.dispose();
                        new LibraryHomePage();
                    }else {
                        JOptionPane.showMessageDialog(null, "Invalid Email Or Password");
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Password should be at least 6 characters");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Enter valid Email Address");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Please enter Email and Password");
        }
    }
    private boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}