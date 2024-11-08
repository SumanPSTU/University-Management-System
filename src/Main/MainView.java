package Main;


import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView(){
        setTitle("Patuakhali Science and Technology University");
        setSize(1350,750);
        setMinimumSize(new Dimension(1350,750));
        setDefaultCloseOperation(MainView.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());







        setVisible(true);
    }
    public static void main(String[] args) {
        new MainView();
    }
}
