package com.recover.admin;

import Main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverAdmin extends JFrame implements ActionListener {

    private JTextField emailField,captchaField;
    private JPasswordField passwordField,confPass;
    private JButton recoverButton;
    private String email,captchaCode;


    public RecoverAdmin(String email, String captchaCode) {

        this.email = email;
        this.captchaCode = captchaCode;

        setTitle("recover admin");
        setSize(500,400);
        setIconImage(new ImageIcon("icon/lock.png").getImage());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);


        //main prompt
        JLabel main = new JLabel("Recover Admin");
        main.setSize(200,30);
        main.setLocation(160,30);
        main.setFont(new Font("Arial",Font.BOLD,25));
        add(main);

        JLabel pass = new JLabel("Enter password");
        pass.setSize(200,30);
        pass.setLocation(70,110);
        pass.setFont(new Font("Arial",Font.PLAIN,16));
        add(pass);

        passwordField = new JPasswordField();
        passwordField.setSize(200,30);
        passwordField.setBorder(new EmptyBorder(0,5,0,5));
        passwordField.setLocation(200,110);
        passwordField.setFont(new Font("Arial",Font.PLAIN,25));
        add(passwordField);

        JLabel passConf = new JLabel("Renter password");
        passConf.setSize(200,30);
        passConf.setLocation(70,160);
        passConf.setFont(new Font("Arial",Font.PLAIN,16));
        add(passConf);

        confPass = new JPasswordField();
        confPass.setSize(200,30);
        confPass.setBorder(new EmptyBorder(0,5,0,5));
        confPass.setLocation(200,160);
        confPass.setFont(new Font("Arial",Font.PLAIN,25));
        add(confPass);

        JLabel caps = new JLabel("Captcha:-");
        caps.setSize(200,30);
        caps.setLocation(70,200);
        caps.setFont(new Font("Arial",Font.PLAIN,16));
        add(caps);

        JLabel cap = new JLabel(captchaCode);
        cap.setSize(200,30);
        cap.setLocation(200,200);
        cap.setFont(new Font("Arial",Font.BOLD,17));
        add(cap);

        JLabel captcha = new JLabel("Enter Captcha");
        captcha.setSize(200,30);
        captcha.setLocation(70,250);
        captcha.setFont(new Font("Arial",Font.PLAIN,17));
        add(captcha);

        captchaField = new JTextField();
        captchaField.setSize(200,30);
        captchaField.setLocation(200,250);
        captchaField.setBorder(new EmptyBorder(0,5,0,5));
        captchaField.setFont(new Font("Arial",Font.PLAIN,16));
        add(captchaField);


        recoverButton = new JButton("Recover");
        recoverButton.setSize(100,30);
        recoverButton.setLocation(210,300);
        recoverButton.setFont(new Font("Arial",Font.PLAIN,17));
        recoverButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recoverButton.addActionListener(this);
        add(recoverButton);

        setVisible(true);
    }

    private boolean checkCaptcha(String captcha) {
        if (captcha.equals(captchaCode)) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recoverButton) {

            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confPass.getPassword());
            String captcha = captchaField.getText();

            //check every input validation
            if (!email.isEmpty() && !captcha.isEmpty()) {
                    if (password.length()>=6 && confirmPassword.length()>=6) {
                        if (password.equals(confirmPassword)) {
                            if (checkCaptcha(captcha)) {
                                SetRecoverPass pass = new SetRecoverPass(password,email);
                                if (pass.successfulUpdate()){
                                    JOptionPane.showMessageDialog(null,"Password recover successful");
                                    new Main();
                                    dispose();
                                }else{
                                    JOptionPane.showMessageDialog(null, "something wrong, please try again");
                                }
                            }else {
                                JOptionPane.showMessageDialog(null, "Enter Captcha carefully");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"Passwords does not match");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Passwords must be at least 6 characters");
                    }
            }else {
                JOptionPane.showMessageDialog(null,"Please fill all field");
            }
        }
    }
}
