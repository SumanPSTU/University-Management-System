package Main;

import ShowData.StudentListView;
import ShowData.TeacherListView;
import SignUpData.StudentSignUpFrame;
import SignUpData.TeacherSignUpFrame;
import attendenceSystem.SmartAttendance;
import library.management.LibrarianSignInFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainView extends JFrame implements ActionListener {
    JPanel rightPanel;
    JPanel leftPanel;
    JPanel topPanel;
    CardLayout cardLayout;

    public MainView() {
        setTitle("Patuakhali Science and Technology University");
        setSize(1350, 750);
        setMinimumSize(new Dimension(1350, 750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());

        // Use BorderLayout for the frame
        setLayout(new BorderLayout());

        // create top panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        topPanel.setBackground(new Color(168, 165, 165));
        add(topPanel, BorderLayout.NORTH);


        // add menu bar with top panel
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Arial",Font.BOLD,20));

        topPanel.add(menuBar);
        // add some menu to menu bar
        JMenu about = new JMenu("About");
        JMenu academics = new JMenu("Academics");
        JMenu admission = new JMenu("Admission");
        JMenu student = new JMenu("Student");



        //menu Item for menu
        //menu item for about
        JMenuItem history = new JMenuItem("History");
        JMenuItem uOrdinance = new JMenuItem("University ordinance");
        JMenuItem atAGlance = new JMenuItem("at a AGlance");
        JMenuItem Chancellor = new JMenuItem("Chancellor");
        JMenuItem viceChancellor = new JMenuItem("vice Chancellor");
        JMenu others = new JMenu("Others");

        //menu item for academics
        JMenuItem academisProgram = new JMenuItem("academic program");
        JMenu undergraduate = new JMenu("Undergraduate Studies");
        JMenuItem postGraduate = new JMenuItem("Postgraduate Studies");
        JMenuItem institutes = new JMenuItem("Institutes");
        JMenuItem academicCalender = new JMenuItem("academic calender");
        JMenuItem facilities = new JMenuItem("Facilities");



        //menu item for admission
        JMenuItem msMba = new JMenuItem("MS/MBA Admission");
        JMenuItem phdAdmiCircular = new JMenuItem("Phd Admission Circular");
        JMenuItem pmbaOrEMBA = new JMenuItem("PMBA/EMBA Admission Notice");
        JMenuItem undAdmission = new JMenuItem("Under graduate Admission");


        //add admission menu item
        admission.add(msMba);
        admission.add(phdAdmiCircular);
        admission.add(pmbaOrEMBA);
        admission.add(undAdmission);



        // add menu item with academics
        academics.add(academisProgram);
        academics.add(undergraduate);
        academics.add(postGraduate);
        academics.add(institutes);
        academics.add(academicCalender);
        academics.add(facilities);

        //menu item for undergraduate
        JMenuItem faculties = new JMenuItem("Faculties");
        JMenuItem departments = new JMenuItem("Departments");
        undergraduate.add(faculties);
        undergraduate.add(departments);

        // add JmenuItem for others

        JMenuItem proViceChancellor = new JMenuItem("Pro Vice Chancellor");
        JMenuItem treasurer = new JMenuItem("Treasurer");
        JMenuItem regentBoard = new JMenuItem("Regent Board");
        JMenuItem academicCouncil = new JMenuItem("Academic Council");
        JMenuItem administrativeOffices = new JMenuItem("Administrative Offices");

        //add all other's item
        others.add(proViceChancellor);
        others.add(treasurer);
        others.add(regentBoard);
        others.add(academicCouncil);
        others.add(administrativeOffices);

        //add menuItem with about menu
        about.add(history);
        about.add(uOrdinance);
        about.add(atAGlance);
        about.add(Chancellor);
        about.add(viceChancellor);
        about.add(others);

        //add menu to menuBar
        menuBar.add(about);
        menuBar.add(new JSeparator());
        menuBar.add(academics);
        menuBar.add(admission);
        menuBar.add(student);


        // Create right panel
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(188, 200, 213));
        add(rightPanel, BorderLayout.CENTER);

        // Create left panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(250, 750));
        leftPanel.setBackground(new Color(141, 173, 218));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        add(leftPanel, BorderLayout.WEST); // Place on the left

        // Add vertical glue before the buttons to center them
        leftPanel.add(Box.createVerticalGlue());

        //make multiple button here
        // everything implement into left panel

        JButton home = createButton("Home");
        home.addActionListener(this);
        leftPanel.add(home);

        JButton dashBoard = createButton("Add Student");
        dashBoard.addActionListener(this);
        leftPanel.add(dashBoard);

        JButton faculty = createButton("Add Teacher");
        faculty.addActionListener(this);
        leftPanel.add(faculty);

        JButton showTeacher = createButton("Show Teacher");
        showTeacher.addActionListener(this);
        leftPanel.add(showTeacher);

        JButton showStudent = createButton("Show Student");
        showStudent.addActionListener(this);
        leftPanel.add(showStudent);

        JButton teacherInfo = createButton("Teacher info");
        teacherInfo.addActionListener(this);
        leftPanel.add(teacherInfo);

        JButton smart = createButton("Attendance");
        smart.addActionListener(this);
        leftPanel.add(smart);

        JButton facultyButton = createButton("Faculty");
        facultyButton.addActionListener(this);
        leftPanel.add(facultyButton);


        JButton search = createButton("Search");
        search.addActionListener(this);
        leftPanel.add(search);




        JButton notice = createButton("Notice");
        notice.addActionListener(this);
        leftPanel.add(notice);

        JButton visitPage = createButton("more info");
        visitPage.addActionListener(this);
        leftPanel.add(visitPage);

        JButton librarian = createButton("Library");
        librarian.addActionListener(this);
        leftPanel.add(librarian);


        JButton logout = createButton("Logout");
        logout.addActionListener(this);
        leftPanel.add(logout);




        leftPanel.add(Box.createVerticalGlue());
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);
        JPanel imagePanel = new JPanel();

        try {
            URL imageUrl = new URL("https://www.pstu.ac.bd/storage/images/faculties/1691302432_fsh.jpg");
            Image image = ImageIO.read(imageUrl).getScaledInstance(1350, 750, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(image);
            imagePanel.add(new JLabel(imageIcon));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading image");
        }

        // Add the image panel to the right panel with a unique identifier
        rightPanel.add(imagePanel, "ImagePanel");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(new Color(107, 236, 73));
        rightPanel.add(panel2,"dashBoard");


        setVisible(true);
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setMaximumSize(new Dimension(150, 35));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        return button;
    }

    public static void main(String[] args) {
        new MainView();
    }


    // add action listener for all button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Home")) {
            cardLayout.show(rightPanel, "ImagePanel");
        }else if (e.getActionCommand().equals("Add Student")) {
            new StudentSignUpFrame();
        }else if (e.getActionCommand().equals("Show Teacher")) {
            new TeacherListView();
        }else if (e.getActionCommand().equals("Show Student")) {
            new StudentListView();
        }else if (e.getActionCommand().equals("Add Teacher")) {
            new TeacherSignUpFrame();
        }
        else if (e.getActionCommand().equals("Dash Board")) {
            cardLayout.show(rightPanel, "dashBoard");
        }else if (e.getActionCommand().equals("Library")) {
            new LibrarianSignInFrame();
        }else if (e.getActionCommand().equals("Attendance")) {
            new SmartAttendance();
        } else if (e.getActionCommand().equals("Notice")) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.pstu.ac.bd/notices"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }

        //action for logout button
        else if (e.getActionCommand().equals("Logout")) {
           int conf = JOptionPane.showConfirmDialog(null,"Are you sure to logout?");
           if (conf == JOptionPane.YES_OPTION) {
               this.dispose();
               System.exit(0);
           }
        }

        //action for more info button
        else if (e.getActionCommand().equals("more info")) {
            try {
                URI uri = new URI("https://www.pstu.ac.bd/");
                Desktop.getDesktop().browse(uri);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null, exception);
            }
        }

        //action for Faculty button
        else if (e.getActionCommand().equals("Faculty")) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.pstu.ac.bd/faculties"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }

        // action for teacher info
        else if (e.getActionCommand().equals("Teacher info")) {
            new TeacherListView();
        }
        else  if (e.getActionCommand().equals("Search")){
            try {
                Desktop.getDesktop().browse(new URI("https://www.pstu.ac.bd/search"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void createAttendance() {

    }
}