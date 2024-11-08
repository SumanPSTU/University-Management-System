package ShowData;

import com.connection.ConnectionData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDataShow extends JFrame {
    public JLabel id,name,add,dob,gender,mob,email,faculty,department;
    JLabel imageLable;
    private final int teacherId;
    public TeacherDataShow(int teacherId){
        this.teacherId = teacherId;
        setTitle("Student Information");
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setSize(1000,700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(178, 178, 218));
        setLayout(null);

        // add university name
        JLabel uni = new JLabel("Patuakhali Science and Technology University");
        uni.setFont(new Font("Arial",Font.BOLD,35));
        uni.setBounds(110,30,760,60);
        add(uni);
        // add heading
        JLabel info = new JLabel("TEACHER INFORMATION");
        info.setBounds(320,90,320,50);
        info.setFont(new Font("Arial",Font.BOLD,25));
        add(info);

        ImageIcon icon = new ImageIcon("image/head.png");
        Image image1 = icon.getImage().getScaledInstance(150,180,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image1);
        imageLable = new JLabel(imageIcon);
        imageLable.setBounds(720,150,150,180);
        add(imageLable);

        JLabel label = new JLabel("Teacher Image");
        label.setBounds(728,300,190,150);
        label.setFont(new Font("Arial",Font.BOLD,19));
        add(label);



        // lable for show information;
        int xPos = 250;
        int yPos = 170;
        add(createLable("Teacher's ID:",xPos,yPos,30,120));
        yPos+=40;

        add(createLable("Teacher's Name:",xPos,yPos,30,150));
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
        add(createLable("Department:",xPos,yPos,30,120));

        // lable for set data
        int xpos = 450;
        int ypos = 170;
        int height = 30;
        int width = 240;
        Font font = new Font("Arial",Font.PLAIN,17);


        id = new JLabel("2202014");
        id.setBounds(xpos,ypos,width,height);
        id.setFont(font);
        add(id);
        ypos+=40;


        name = new JLabel("Suman Das");
        name.setBounds(xpos,ypos,width,height);
        name.setFont(font);
        add(name);
        ypos+=40;


        add = new JLabel("2202014");
        add.setBounds(xpos,ypos,width,height);
        add.setFont(font);
        add(add);
        ypos+=40;

        dob = new JLabel("2202014");
        dob.setBounds(xpos,ypos,width,height);
        dob.setFont(font);
        add(dob);
        ypos+=40;

        gender = new JLabel("2202014");
        gender.setBounds(xpos,ypos,width,height);
        gender.setFont(font);
        add(gender);
        ypos+=40;

        mob = new JLabel("2202014");
        mob.setBounds(xpos,ypos,width,height);
        mob.setFont(font);
        add(mob);
        ypos+=40;

        email = new JLabel("2202014");
        email.setBounds(xpos,ypos,width,height);
        email.setFont(font);
        add(email);
        ypos+=40;


        faculty = new JLabel("2202014");
        faculty.setBounds(xpos,ypos,width,height);
        faculty.setFont(font);
        add(faculty);
        ypos+=40;

        department = new JLabel("2202014");
        department.setBounds(xpos,ypos,width,height);
        department.setFont(font);
        add(department);





        // add result button
        JButton more = new JButton("more");
        more.setBounds(650,550,100,30);
        more.setFont(new Font("Arial",Font.PLAIN,17));
        add(more);

        //add close button to dispose this page
        JButton close = new JButton("close");
        close.setBounds(770,550,100,30);
        close.setFont(new Font("Arial",Font.PLAIN,17));
        add(close);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setData(this.teacherId);
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
            String query = "select * from university.teacher_info where teacher_id = ?";
            PreparedStatement pre = connection.con.prepareStatement(query);
            pre.setInt(1,studentId);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()){

                //retrieve data from database
                id.setText(String.valueOf(resultSet.getInt("teacher_id")));
                name.setText(resultSet.getString("name"));
                dob.setText(resultSet.getString("date_of_birth"));
                gender.setText(resultSet.getString("gender"));
                mob.setText(resultSet.getString("phone_no"));
                email.setText(resultSet.getString("email"));
                faculty.setText(resultSet.getString("faculty"));
                department.setText(resultSet.getString("department"));


                //Display the image in the JLabel
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
    public static void main(String[] args){
        new TeacherDataShow(2);
    }
}
