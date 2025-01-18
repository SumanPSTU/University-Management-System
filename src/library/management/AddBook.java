package library.management;

import javax.swing.*;
import java.awt.*;

public class AddBook extends JFrame {
    public AddBook() {
        setTitle("Add Book");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(new ImageIcon("icon/stackofbooks.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(getJlable("Lable",0,40,100,30));



        // set a background image
        ImageIcon icon = new ImageIcon("icon/book.jpg");
        Image image = icon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(image);
        JLabel backLabel = new JLabel(icon1);
        backLabel.setBounds(0, 0, 700, 500);
        add(backLabel);

        setVisible(true);
    }
    public static JLabel getJlable(String title,int x,int y,int width,int height) {
        JLabel label = new JLabel(title);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        return label;
    }
    public static JTextField getJTextField(String title,int x,int y,int width,int height){
        JTextField textField = new JTextField(title);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Aria" +
                "l", Font.BOLD, 18));
        return textField;
    }
    public static void main(String[] args) {
        new AddBook();
    }
}
