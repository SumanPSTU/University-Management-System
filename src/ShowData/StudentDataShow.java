package ShowData;

import com.connection.ConnectionData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDataShow extends JFrame {
    public JLabel id,regi,name,fName,mName,add,dob,gender,mob,email,faculty,semister,image;
    JLabel imageLable;
    private int stdId;
    public StudentDataShow(int stdId){
        this.stdId = stdId;
        setTitle("Student Information");
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setSize(1000,750);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getContentPane().setBackground(new Color(178, 178, 218));
        setLayout(null);

        // add university name
        JLabel uni = new JLabel("Patuakhali Science and Technology University");
        uni.setFont(new Font("Arial",Font.BOLD,35));
        uni.setBounds(110,30,760,60);
        add(uni);
        // add heading
        JLabel info = new JLabel("STUDENT INFORMATION");
        info.setBounds(350,90,300,50);
        info.setFont(new Font("Arial",Font.BOLD,25));
        add(info);

        ImageIcon icon = new ImageIcon("image/head.png");
        Image image1 = icon.getImage().getScaledInstance(150,180,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image1);
        imageLable = new JLabel(imageIcon);
        imageLable.setBounds(720,150,150,180);
        add(imageLable);

        JLabel label = new JLabel("Student Image");
        label.setBounds(728,300,190,150);
        label.setFont(new Font("Arial",Font.BOLD,19));
        add(label);



        // lable for show information;
        int xPos = 250;
        int yPos = 170;

        add(createLable("Student ID:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Reg No:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Student Name:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Father's Name:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Mother's Name:",xPos,yPos,30,120));

        yPos+=40;
        add(createLable("Address:",xPos,yPos,30,120));

        yPos+=40;
        add(createLable("Date of birth:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Gender:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Mobile No:",xPos,yPos,30,120));

        yPos+=40;
        add(createLable("Email:",xPos,yPos,30,120));

        yPos+=40;
        add(createLable("Faculty:",xPos,yPos,30,120));
        yPos+=40;
        add(createLable("Semister:",xPos,yPos,30,120));

        // lable for set data
        int xpos = 450;
        int ypos = 170;
        int height = 30;
        int width = 180;
        Font font = new Font("Arial",Font.PLAIN,17);


        id = new JLabel("");
        id.setBounds(xpos,ypos,width,height);
        id.setFont(font);
        add(id);
        ypos+=40;

        regi = new JLabel("");
        regi.setBounds(xpos,ypos,width,height);
        regi.setFont(font);
        add(regi);
        ypos+=40;

        name = new JLabel("");
        name.setBounds(xpos,ypos,width,height);
        name.setFont(font);
        add(name);
        ypos+=40;

        fName = new JLabel("");
        fName.setBounds(xpos,ypos,width,height);
        fName.setFont(font);
        add(fName);
        ypos+=40;

        mName = new JLabel("");
        mName.setBounds(xpos,ypos,width,height);
        mName.setFont(font);
        add(mName);
        ypos+=40;

        add = new JLabel("");
        add.setBounds(xpos,ypos,width,height);
        add.setFont(font);
        add(add);
        ypos+=40;

        dob = new JLabel("");
        dob.setBounds(xpos,ypos,width,height);
        dob.setFont(font);
        add(dob);
        ypos+=40;

        gender = new JLabel("");
        gender.setBounds(xpos,ypos,width,height);
        gender.setFont(font);
        add(gender);
        ypos+=40;

        mob = new JLabel("");
        mob.setBounds(xpos,ypos,width,height);
        mob.setFont(font);
        add(mob);
        ypos+=40;

        email = new JLabel("");
        email.setBounds(xpos,ypos,width,height);
        email.setFont(font);
        add(email);
        ypos+=40;

        faculty = new JLabel("");
        faculty.setBounds(xpos,ypos,width,height);
        faculty.setFont(font);
        add(faculty);
        ypos+=40;

        semister = new JLabel("");
        semister.setBounds(xpos,ypos,width,height);
        semister.setFont(font);
        add(semister);
        ypos+=40;


        // add result button
        JButton seeResult = new JButton("result");
        seeResult.setBounds(650,650,100,30);
        seeResult.setFont(new Font("Arial",Font.PLAIN,17));
        add(seeResult);

        JButton close = new JButton("close");
        close.setBounds(770,650,100,30);
        close.setFont(new Font("Arial",Font.PLAIN,17));
        add(close);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setData(this.stdId);
        setVisible(true);
    }

    public JLabel createLable(String text,int xPos,int yPos,int height, int weight){
        JLabel label = new JLabel(text+"-");
        label.setBounds(xPos,yPos,weight,height);
        label.setFont(new Font("Arial",Font.PLAIN,17));
        return label;
    }
    public void setData(int studentId){
        ConnectionData connection = new ConnectionData();
        try {
            String query = "select * from university.student_info where id = ?";
            PreparedStatement pre = connection.con.prepareStatement(query);
            pre.setInt(1,studentId);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()){
                id.setText(String.valueOf(resultSet.getInt("id")));
                regi.setText(String.valueOf(resultSet.getInt("regi_no")));
                name.setText(resultSet.getString("name"));
                fName.setText(resultSet.getString("father_name"));
                mName.setText(resultSet.getString("mother_name"));
                add.setText(resultSet.getString("present_add"));
                dob.setText(resultSet.getString("date_of_birth"));
                gender.setText(resultSet.getString("gender"));
                mob.setText(resultSet.getString("mobile_no"));
                email.setText(resultSet.getString("email"));
                faculty.setText(resultSet.getString("faculty"));
                semister.setText(resultSet.getString("semister"));

                // Display the image in the JLabel
                ImageIcon icon = new ImageIcon("image/"+resultSet.getString("image"));
                Image img = icon.getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH);
                icon.setImage(img);
                imageLable.setIcon(new ImageIcon(img));

            }else {
                System.out.println("data not found");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
