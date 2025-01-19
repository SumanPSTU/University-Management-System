package library.management;

import javax.swing.*;
import java.awt.*;

public class LibraryHomePage extends JFrame {
    private JPanel leftPanel;
    private JPanel namePanel;
    private JPanel cardPanel;
    private CardLayout mainCardLayout;
    private JPanel homePanel;

    public LibraryHomePage() {
        setTitle("Library Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon("icon/stackofbooks.png").getImage());
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 200, 700);
        leftPanel.setLayout(null);
        ImageIcon backIcon = new ImageIcon("icon/left.jpg");
        Image backImage = backIcon.getImage().getScaledInstance(200, 700, Image.SCALE_SMOOTH);
        JLabel backLable = new JLabel(new ImageIcon(backImage));
        backLable.setSize(200, 700);
        leftPanel.add(backLable);

        // Adding buttons to the left panel
        JButton homeButton = createButton("Home",150);
        homeButton.setBounds(30,150,140,30);
        leftPanel.add(homeButton);


        JButton aboutButton = createButton("Dashboard",190);
        leftPanel.add(aboutButton);

        add(leftPanel);

        // Name Panel
        namePanel = new JPanel();
        namePanel.setBounds(200, 5, 700, 50);
        namePanel.setBackground(new Color(231, 165, 246, 140));

        JLabel nameLabel = new JLabel("Welcome to Central Library");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 33));
        namePanel.add(nameLabel);
        add(namePanel);

        // Card Layout Panel
        mainCardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setBounds(200, 48, 700, 650);
        cardPanel.setLayout(mainCardLayout);

        // Home Panel
        homePanel = new JPanel();
        ImageIcon homeIcon = new ImageIcon("icon/libraryHome.jpg");
        Image homeImage = homeIcon.getImage().getScaledInstance(700, 650, Image.SCALE_SMOOTH);

        JLabel homeLabel = new JLabel(new ImageIcon(homeImage));
        homePanel.add(homeLabel);

        cardPanel.add(homePanel, "Home");
        add(cardPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryHomePage();
    }

    // Method for creating a button
    public JButton createButton(String text,int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));

        return button;
    }
}
