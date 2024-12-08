package Main;

import ShowData.TeacherListView;

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

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(1350, 50));
        add(topPanel, BorderLayout.NORTH);

        // Create right panel
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(188, 200, 213));
        add(rightPanel, BorderLayout.CENTER);

        // Create left panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(250, 750));
        leftPanel.setBackground(new Color(158, 163, 156));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        add(leftPanel, BorderLayout.WEST); // Place on the left

        // Add vertical glue before the buttons to center them
        leftPanel.add(Box.createVerticalGlue());

        //make multiple button here
        // everything implement into left panel

        JButton home = createButton("Home");
        home.addActionListener(this);
        leftPanel.add(home);

        JButton dashBoard = createButton("Dash Board");
        dashBoard.addActionListener(this);
        leftPanel.add(dashBoard);

        JButton search = createButton("Search");
        search.addActionListener(this);
        leftPanel.add(search);

        JButton teacherInfo = createButton("Teacher info");
        teacherInfo.addActionListener(this);
        leftPanel.add(teacherInfo);

        JButton visitPage = createButton("more info");
        visitPage.addActionListener(this);
        leftPanel.add(visitPage);

        JButton faculty = createButton("Faculty");
        faculty.addActionListener(this);
        leftPanel.add(faculty);


        JButton logout = createButton("Logout");
        logout.addActionListener(this);
        leftPanel.add(logout);

        // Add vertical glue after the buttons to center them
        leftPanel.add(Box.createVerticalGlue());

        //add everything in right panel
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);
        JPanel imagePanel = new JPanel();

        try {
            URL imageUrl = new URL("https://www.pstu.ac.bd/storage/images/faculties/1691302432_fsh.jpg");
            Image image = ImageIO.read(imageUrl).getScaledInstance(1350, 750, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(image);
            imagePanel.add(new JLabel(imageIcon));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
        }

        // Add the image panel to the right panel with a unique identifier
        rightPanel.add(imagePanel, "ImagePanel");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(new Color(84, 94, 200));
        rightPanel.add(panel2,"Blue");


        setVisible(true);
    }

    private JButton createButton(String text) {
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
        // action for home button
        if (e.getActionCommand().equals("Home")) {
            cardLayout.show(rightPanel, "Blue");
        }

        else if (e.getActionCommand().equals("Dash Board")) {
            cardLayout.show(rightPanel, "green");
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
}