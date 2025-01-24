package library.management;

import javax.naming.ldap.PagedResultsControl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import Main.MainView;
import com.connection.ConnectionData;

public class LibraryHomePage extends JFrame implements ActionListener {
    private JPanel leftPanel;
    private JPanel cardPanel;
    private CardLayout mainCardLayout;
    private JPanel homePanel, addBookPanel, allBooksPanel,updatePanel;
    private JPanel dashboardPanel;
    private Font font;
    private JTable table;
    private JDialog dialog;

    private JButton insertButton,updateButton;

    private JTextField isbnField, titleField, authorField, copyField, publishYearField;
    private JTextField isbnField1, titleField1, authorField1, copyField1, publishYearField1;

    public LibraryHomePage() {
        setTitle("Library Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon("icon/stackofbooks.png").getImage());
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Create font object for further use
        font = new Font("Serif", Font.PLAIN, 17);

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 200, 700);
        leftPanel.setLayout(null);

        // Adding buttons to the left panel
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(30, 150, 140, 30);

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(30, 190, 140, 30);

        JButton allBooksButton = new JButton("All Books");
        allBooksButton.setBounds(30, 230, 140, 30);

        JButton addBooksButton = new JButton("Add Books");
        addBooksButton.setBounds(30, 270, 140, 30);

        JButton deleteBooksButton = new JButton("Delete Books");
        deleteBooksButton.setBounds(30, 310, 140, 30);

        JButton updateBooksButton = new JButton("Update Books");
        updateBooksButton.setBounds(30, 350, 140, 30);

        JButton browseBooksButton = new JButton("Browse Books");
        browseBooksButton.setBounds(30, 390, 140, 30);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(30, 430, 140, 30);

        // Add all components to leftPanel
        leftPanel.add(homeButton);
        leftPanel.add(dashboardButton);
        leftPanel.add(allBooksButton);
        leftPanel.add(addBooksButton);
        leftPanel.add(deleteBooksButton);
        leftPanel.add(updateBooksButton);
        leftPanel.add(browseBooksButton);
        leftPanel.add(logoutButton);

        // Background image for the left panel
        ImageIcon backIcon = new ImageIcon("icon/left.jpg");
        Image backImage = backIcon.getImage().getScaledInstance(200, 700, Image.SCALE_SMOOTH);
        JLabel backLabel = new JLabel(new ImageIcon(backImage));
        backLabel.setSize(200, 700);
        leftPanel.add(backLabel);

        // Card Layout Panel
        mainCardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setBounds(200, 0, 700, 680);
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

        // Add Book Panel
        addBookPanel = new JPanel();
        addBookPanel.setLayout(null);
        addBookPanel.setBackground(new Color(208, 200, 211));

        // Components for Add Books panel
        JLabel addBookLabel = new JLabel("Add Books");
        addBookLabel.setFont(new Font("Arial", Font.BOLD, 32));
        addBookLabel.setBounds(240, 50, 400, 40);
        addBookPanel.add(addBookLabel);

        JLabel isbnLabel = new JLabel("ISBN Number:");
        isbnLabel.setFont(font);
        isbnLabel.setBounds(100, 150, 170, 30);
        addBookPanel.add(isbnLabel);

        isbnField = new JTextField();
        isbnField.setFont(font);
        isbnField.setBounds(300, 150, 190, 30);
        addBookPanel.add(isbnField);

        JLabel titleLabel = new JLabel("Book Title:");
        titleLabel.setFont(font);
        titleLabel.setBounds(100, 210, 170, 30);
        addBookPanel.add(titleLabel);

        titleField = new JTextField();
        titleField.setFont(font);
        titleField.setBounds(300, 210, 190, 30);
        addBookPanel.add(titleField);

        JLabel copyLabel = new JLabel("Number of Copies:");
        copyLabel.setFont(font);
        copyLabel.setBounds(100, 270, 170, 30);
        addBookPanel.add(copyLabel);

        copyField = new JTextField();
        copyField.setFont(font);
        copyField.setBounds(300, 270, 190, 30);
        addBookPanel.add(copyField);

        JLabel authorLabel = new JLabel("Author Name:");
        authorLabel.setFont(font);
        authorLabel.setBounds(100, 330, 170, 30);
        addBookPanel.add(authorLabel);

        authorField = new JTextField();
        authorField.setFont(font);
        authorField.setBounds(300, 330, 190, 30);
        addBookPanel.add(authorField);

        JLabel publishYearLabel = new JLabel("Publish Year:");
        publishYearLabel.setFont(font);
        publishYearLabel.setBounds(100, 390, 170, 30);
        addBookPanel.add(publishYearLabel);

        publishYearField = new JTextField();
        publishYearField.setFont(font);
        publishYearField.setBounds(300, 390, 190, 30);
        addBookPanel.add(publishYearField);

        insertButton = new JButton("Insert Book");
        insertButton.setFont(font);
        insertButton.setBounds(250, 450, 150, 35);
        addBookPanel.add(insertButton);



        // component for update panel

        updatePanel = new JPanel();
        updatePanel.setBackground(new Color(182, 181, 182));
        updatePanel.setLayout(null);

        updatePanel.add(isbnLabel);
        updatePanel.add(titleLabel);
        updatePanel.add(copyLabel);
        updatePanel.add(authorLabel);
        updatePanel.add(publishYearLabel);


        isbnField1 = new JTextField();
        isbnField1.setFont(font);
        isbnField1.setBounds(300, 150, 190, 30);
        updatePanel.add(isbnField1);



        titleField1 = new JTextField();
        titleField1.setFont(font);
        titleField1.setBounds(300, 210, 190, 30);
        updatePanel.add(titleField1);

        authorField1 = new JTextField();
        authorField1.setFont(font);
        authorField1.setBounds(300, 330, 190, 30);
        updatePanel.add(authorField1);

        copyField1 = new JTextField();
        copyField1.setFont(font);
        copyField1.setBounds(300, 270, 190, 30);
        updatePanel.add(copyField1);

        publishYearField1 = new JTextField();
        publishYearField1.setFont(font);
        publishYearField1.setBounds(300, 390, 190, 30);
        updatePanel.add(publishYearField1);


        updateButton = new JButton("Update Book");
        updateButton.setFont(font);
        updateButton.setBounds(250, 470, 150, 35);
        updatePanel.add(updateButton);

        // update component panel
        insertButton.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                int isbn = Integer.parseInt(isbnField.getText().trim());
                int year = Integer.parseInt(publishYearField.getText().trim());
                int copies = Integer.parseInt(copyField.getText().trim());

                if (!title.isEmpty() && !author.isEmpty() && copies > 0) {
                    if (!new LibraryConn().ifIsbnExist(isbn)) {
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this book?");
                        if (option == JOptionPane.YES_OPTION) {
                            new AddBook(title, author, year, copies, isbn);
                            JOptionPane.showMessageDialog(null, "Book added successfully!");
                            setFieldNull();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Book already exists!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Something went wrong!");
            }
        });

        // Create panel for showing book panel
        allBooksPanel = new JPanel();
        allBooksPanel.setBackground(new Color(174, 52, 205));
        allBooksPanel.setLayout(new BorderLayout());

        // Add scroll pane to all books panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 100, 850, 630);

        // Set up the initial empty JTable
        table = new JTable();
        scrollPane.setViewportView(table);
        allBooksPanel.add(scrollPane, BorderLayout.CENTER);

        // add update panel





        cardPanel.add(homePanel, "Home");
        cardPanel.add(dashboardPanel, "Dashboard");
        cardPanel.add(addBookPanel, "Add Books");
        cardPanel.add(allBooksPanel, "All Books");
        cardPanel.add(updatePanel, "update panels");

        add(cardPanel);
        add(leftPanel);

        // Add action listeners for buttons
        homeButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        allBooksButton.addActionListener(this);
        browseBooksButton.addActionListener(this);
        addBooksButton.addActionListener(this);
        updateBooksButton.addActionListener(this);
        logoutButton.addActionListener(this);

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
                retrieveDataFromDatabase();
                mainCardLayout.show(cardPanel, "All Books");
                break;
            case "Add Books":
                mainCardLayout.show(cardPanel, "Add Books");
                break;

            case "Update Books":
                    mainCardLayout.show(cardPanel, "update panels");
                    openDialog();

                    break;
            case "Logout":
                new MainView();
                dispose();
                break;
        }
    }

    private void retrieveDataFromDatabase() {
        // Column names
        String[] columns = {"ISBN", "Book Title", "Copies", "Author", "Publish Year"};
        LibraryConn connection = new LibraryConn();
        try {
            String query = "SELECT * FROM library.books";

            // Execute the query
            ResultSet resultSet = connection.con.createStatement().executeQuery(query);

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("isbn"));
                row.add(resultSet.getString("title"));
                row.add(resultSet.getInt("numofcopy"));
                row.add(resultSet.getString("author"));
                row.add(resultSet.getInt("publishyear"));
                data.add(row);
            }

            // Create the DefaultTableModel with the fetched data
            DefaultTableModel model = new DefaultTableModel(data, new Vector<>(Arrays.asList(columns)));
            table.setModel(model);

            // Ensure the table is properly refreshed to show the text
            table.revalidate();
            table.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data from database: " + e.getMessage());
        }
    }

    public void setFieldNull() {
        isbnField.setText("");
        titleField.setText("");
        authorField.setText("");
        copyField.setText("");
        publishYearField.setText("");
    }
    public void openDialog(){
        dialog = new JDialog();
        dialog.setTitle("Update Books");
        dialog.setIconImage(new ImageIcon("icon/stackofbooks.png").getImage());
        dialog.setSize(300,200);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().setBackground(new Color(204, 202, 204));
        dialog.setLayout(null);

        JLabel label = new JLabel("Enter ISBN Number");
        label.setBounds(40, 30, 200, 30);
        label.setFont(font);
        dialog.add(label);

        JTextField isbnField = new JTextField();
        isbnField.setBounds(40,60,200,30);
        isbnField.setFont(font);
        dialog.add(isbnField);

        JButton button = new JButton("Check");
        button.setBounds(90,100,100,30);
        button.setFont(font);
        dialog.add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int isbn = Integer.parseInt(isbnField.getText());
                if (ifInt(String.valueOf(isbn))) {
                    if (new LibraryConn().ifIsbnExist(isbn)) {

                        // call method for setData to the update field

                        setDataToUpdatepanel(isbn);
                        dialog.setVisible(false);
                    }else {
                        JOptionPane.showMessageDialog(null,"ISBN not found");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"ISBN must be Integer");
                }

            }
        });
        dialog.setVisible(true);

    }
    private boolean ifInt(String id){
        return id.matches("[0-9]+");
    }
    public void setDataToUpdatepanel(int isbn) {
        LibraryConn connection = new LibraryConn();
        String query = "SELECT * FROM library.books WHERE isbn=?";
        try {
           PreparedStatement preparedStatement = connection.con.prepareStatement(query);
           preparedStatement.setInt(1, isbn);
           ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isbnField1.setText(String.valueOf(resultSet.getInt("isbn")));
                titleField1.setText(resultSet.getString("title"));
                copyField1.setText(String.valueOf(resultSet.getInt("numofcopy")));
                authorField1.setText(resultSet.getString("author"));
                publishYearField1.setText(String.valueOf(resultSet.getInt("publishyear")));

            }
        }catch (SQLException exception){
            exception.printStackTrace();

        }
    }

}