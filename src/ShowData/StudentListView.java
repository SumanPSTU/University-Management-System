package ShowData;

import com.connection.ConnectionData;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Vector;

public class StudentListView extends JFrame {

    JPanel rightPanel = new JPanel();
    JPanel leftPanel = new JPanel();

    // JTable to display teacher data
    JTable table;

    public StudentListView() {
        setTitle("Student List");
        setSize(1200, 730);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setLayout(null);

        JLabel uni = new JLabel("Patuakhali Science and Technology University");
        uni.setFont(new Font("Arial", Font.BOLD, 29));
        uni.setBounds(100, 10, 800, 50);

        JLabel list = new JLabel("Student's List");
        list.setFont(new Font("Arial", Font.BOLD, 25));
        list.setBounds(330, 60, 200, 30);

        // Left panel
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 350, 730);
        leftPanel.setBackground(new Color(224, 236, 136));
        add(leftPanel);

        // Right panel
        rightPanel.setLayout(null);
        rightPanel.setBounds(350, 0, 850, 730);
        rightPanel.setBackground(new Color(170, 205, 203));
        add(rightPanel);

        rightPanel.add(uni);
        rightPanel.add(list);

        // Create a scroll pane to hold the JTable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 100, 850, 630);
        rightPanel.add(scrollPane);

        // Set up the initial empty JTable
        table = new JTable();
        scrollPane.setViewportView(table);

        setVisible(true);

        // Retrieve data from the database
        retrieveDataFromDatabase();
    }

    private void retrieveDataFromDatabase() {
        // Column names
        String[] columns = {"ID","Regi No", "Name", "Faculty", "Semester", "Email", "Phone No", "More Info"};

        try {
            // Assuming ConnectionData is a utility class that manages the DB connection
            ConnectionData connection = new ConnectionData();
            String query = "SELECT id, regi_no,name, faculty,semister, email, mobile_no FROM university.student_info";

            // Execute the query
            ResultSet resultSet = connection.con.createStatement().executeQuery(query);

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getInt("regi_no"));
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("faculty"));
                row.add(resultSet.getString("semister"));
                row.add(resultSet.getString("email"));
                row.add(resultSet.getString("mobile_no"));

                // Add a clickable text ("More Info") in the "More Info" column
                row.add("More Info");
                data.add(row);
            }

            // Create the DefaultTableModel with the fetched data
            DefaultTableModel model = new DefaultTableModel(data, new Vector<>(Arrays.asList(columns)));
            table.setModel(model);

            // Ensure the table is properly refreshed to show the text
            table.revalidate();
            table.repaint();

            // Set the column to have a specific width for "More Info"
            TableColumn column = table.getColumn("More Info");
            column.setPreferredWidth(100);  // Adjust width as needed

            // Set the custom renderer for the "More Info" column to display clickable text
            column.setCellRenderer(new TextRenderer());

            // Add mouse listener to handle text clicks
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());

                    // Check if the click was in the "More Info" column
                    if (col == table.getColumn("More Info").getModelIndex()) {
                        // Get the teacher's ID from the clicked row
                        int studentId = (int) table.getValueAt(row, 0);
                        new StudentDataShow(studentId);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Custom renderer to display text ("More Info") in the table cell
    static class TextRenderer extends JLabel implements TableCellRenderer {
        public TextRenderer() {
            setOpaque(true); // Ensure the text is displayed correctly
            setForeground(Color.BLUE);  // Make text appear in blue (like a link)
            setFont(new Font("Arial", Font.PLAIN, 16));
            setHorizontalAlignment(SwingConstants.CENTER); // Center align text
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Set the text of the cell to the value in the "More Info" column
            setText((value == null) ? "" : value.toString());
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            // Change the cursor to hand when hovering over the text
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            return this;
        }
    }
    public static void main(String[] args) {
        new StudentListView();
    }
}
