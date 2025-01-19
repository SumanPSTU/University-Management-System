package library.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.MainView;

public class LibraryHomePage extends JFrame implements ActionListener {
    private JPanel leftPanel;
    private JPanel namePanel;
    private JPanel cardPanel;
    private CardLayout mainCardLayout;
    private JPanel homePanel;
    private JPanel dashboardPanel;

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


        // Adding buttons to the left panel
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(30, 150, 140, 30);

        JButton dashboard = new JButton("Dashboard");
        dashboard.setBounds(30, 190, 140, 30);

        JButton allBooksButton = new JButton("All Books");
        allBooksButton.setBounds(30, 230, 140, 30);

        JButton addBooks = new JButton("Add Books");
        addBooks.setBounds(30, 270, 140, 30);

        JButton deleteBooks = new JButton("Delete Books");
        deleteBooks.setBounds(30, 310, 140, 30);

        JButton updateBooks = new JButton("Update Books");
        updateBooks.setBounds(30, 350, 140, 30);



        JButton seeStudentsBroowBook = new JButton("Brow Books");
        seeStudentsBroowBook.setBounds(30, 390, 140, 30);

        JButton logout = new JButton("Logout");
        logout.setBounds(30, 430, 140, 30);

        // add all components to leftPanel
        leftPanel.add(homeButton);
        leftPanel.add(dashboard);
        leftPanel.add(allBooksButton);
        leftPanel.add(addBooks);
        leftPanel.add(deleteBooks);
        leftPanel.add(updateBooks);
        leftPanel.add(seeStudentsBroowBook);
        leftPanel.add(logout);

        ImageIcon backIcon = new ImageIcon("icon/left.jpg");
        Image backImage = backIcon.getImage().getScaledInstance(200, 700, Image.SCALE_SMOOTH);
        JLabel backLable = new JLabel(new ImageIcon(backImage));
        backLable.setSize(200, 700);
        leftPanel.add(backLable);

        // Name Panel
        namePanel = new JPanel();
        namePanel.setBounds(200, 5, 700, 50);
        namePanel.setBackground(new Color(231, 165, 246, 140));

        JLabel nameLabel = new JLabel("Welcome to Central Library");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 33));
        namePanel.add(nameLabel);

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

        // Dashboard Panel
        dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Dashboard Content Here"));

        cardPanel.add(homePanel, "Home");
        cardPanel.add(dashboardPanel, "Dashboard");



        add(namePanel);
        add(cardPanel);
        add(leftPanel);

        homeButton.addActionListener(this);
        dashboard.addActionListener(this);
        allBooksButton.addActionListener(this);
        seeStudentsBroowBook.addActionListener(this);
        addBooks.addActionListener(this);
        updateBooks.addActionListener(this);
        logout.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryHomePage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Home":
                mainCardLayout.show(cardPanel, "Home");
                break;
            case "Dashboard":
                mainCardLayout.show(cardPanel, "Dashboard");
                break;
            case "All Books":
                JOptionPane.showMessageDialog(this, "All Books Button Clicked!");
                break;
            case "Logout":
                new MainView();
                dispose();
                break;
        }
    }
}
