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

public class LibraryHomePage extends JFrame implements ActionListener {
    private JPanel leftPanel;
    private JPanel cardPanel;
    private CardLayout mainCardLayout;
    private JPanel homePanel, addBookPanel, allBooksPanel,updatePanel;
    private JPanel dashboardPanel;
    private Font font;
    private JTable table;
    private JDialog dialog,dialog1;

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



        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(30, 390, 140, 30);

        // Add all components to leftPanel
        leftPanel.add(homeButton);
        leftPanel.add(dashboardButton);
        leftPanel.add(allBooksButton);
        leftPanel.add(addBooksButton);
        leftPanel.add(deleteBooksButton);
        leftPanel.add(updateBooksButton);
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

        JLabel addBookLabel1 = new JLabel("Update Books");
        addBookLabel1.setFont(new Font("Arial", Font.BOLD, 32));
        addBookLabel1.setBounds(240, 50, 400, 40);
        updatePanel.add(addBookLabel1);

        JLabel isbnLabel1 = new JLabel("ISBN Number:");
        isbnLabel1.setFont(font);
        isbnLabel1.setBounds(100, 150, 170, 30);
        updatePanel.add(isbnLabel1);

        JLabel titleLabel1 = new JLabel("Book Title:");
        titleLabel1.setFont(font);
        titleLabel1.setBounds(100, 210, 170, 30);
        updatePanel.add(titleLabel1);

        JLabel copyLabel1 = new JLabel("Number of Copies:");
        copyLabel1.setFont(font);
        copyLabel1.setBounds(100, 270, 170, 30);
        updatePanel.add(copyLabel1);

        JLabel authorLabel1 = new JLabel("Author Name:");
        authorLabel1.setFont(font);
        authorLabel1.setBounds(100, 330, 170, 30);
        updatePanel.add(authorLabel1);

        JLabel publishYearLabel1 = new JLabel("Publish Year:");
        publishYearLabel1.setFont(font);
        publishYearLabel1.setBounds(100, 390, 170, 30);
        updatePanel.add(publishYearLabel1);


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
        updateButton.addActionListener(e -> {
            try {
                String title = titleField1.getText().trim();
                String author = authorField1.getText().trim();
                int isbn = Integer.parseInt(isbnField1.getText().trim());
                int year = Integer.parseInt(publishYearField1.getText().trim());
                int copies = Integer.parseInt(copyField1.getText().trim());

                // check field validity for update data


                if (!title.isEmpty() && !author.isEmpty() && (copies > 0) && year > 0 ) {
                    if (new LibraryConn().ifIsbnExist(isbn)) {
                        if (new UpdateBook(title,author,year,isbn,copies).updateBook()){
                            JOptionPane.showMessageDialog(null, "Book updated successfully");
                            int option = JOptionPane.showConfirmDialog(null,"Do you want to update more?");
                            if (option == JOptionPane.YES_OPTION) {
                                openDialog();

                            }else {
                                setfieldNull();
                                retrieveDataFromDatabase();
                                mainCardLayout.show(cardPanel, "All Books");
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(rootPane, "Please provide liggle ISBN number");
                    }

                }else {
                    JOptionPane.showMessageDialog(null,"Please fill all the field");
                }


            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Something went wrong!");
            }
        });

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
        deleteBooksButton.addActionListener(this);
        addBooksButton.addActionListener(this);
        updateBooksButton.addActionListener(this);
        logoutButton.addActionListener(this);

        setVisible(true);
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
            case "Delete Books":
                    deleteBook();
                break;

            case "Update Books":
                    openDialog();
                    break;
            case "Logout":
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
    public void setfieldNull(){
        isbnField1.setText("");
        titleField1.setText("");
        authorField1.setText("");
        copyField1.setText("");
        publishYearField1.setText("");
    }

    // open dialog for update book list
    public void openDialog(){
        dialog = new JDialog();
        dialog.setTitle("Update Books");
        dialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
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
                        mainCardLayout.show(cardPanel, "update panels");
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
    public void deleteBook() {
        dialog1 = new JDialog();
        dialog1.setTitle("Delete Books");
        dialog1.setIconImage(new ImageIcon("icon/stackofbooks.png").getImage());
        dialog1.setSize(300,200);
        dialog1.setModal(true);
        dialog1.setLocationRelativeTo(null);
        dialog1.getContentPane().setBackground(new Color(204, 202, 204));
        dialog1.setLayout(null);

        JLabel label = new JLabel("Enter ISBN Number");
        label.setBounds(40, 30, 200, 30);
        label.setFont(font);
        dialog1.add(label);

        JTextField isbnField = new JTextField();
        isbnField.setBounds(40,60,200,30);
        isbnField.setFont(font);
        dialog1.add(isbnField);

        JButton button = new JButton("Check");
        button.setBounds(90,100,100,30);
        button.setFont(font);
        dialog1.add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int isbn = Integer.parseInt(isbnField.getText());
                if (ifInt(String.valueOf(isbn))) {
                    if (new LibraryConn().ifIsbnExist(isbn)) {

                        // call method for setData to the update field
                       int option = JOptionPane.showConfirmDialog(null,"Are you sure?");
                       if (option == JOptionPane.YES_OPTION) {
                           if (new DeleteBook(isbn).deleteData()){
                               JOptionPane.showMessageDialog(null,"Delete Successful");
                               int op = JOptionPane.showConfirmDialog(null,"Do you want to delete more?");

                               if (op == JOptionPane.YES_OPTION) {
                                   deleteBook();
                               }else {
                                   dialog1.setVisible(false);
                                   mainCardLayout.show(cardPanel,"Home");
                                   retrieveDataFromDatabase();
                                   mainCardLayout.show(cardPanel, "All Books");
                               }
                           }else {
                               dialog1.setVisible(false);
                               retrieveDataFromDatabase();
                               mainCardLayout.show(cardPanel,"All Books");
                           }
                       }
                    }else {
                        JOptionPane.showMessageDialog(null,"ISBN not found");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"ISBN must be Integer");
                }

            }
        });
        dialog1.setVisible(true);
    }

}