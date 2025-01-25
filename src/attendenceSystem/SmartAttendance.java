package attendenceSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.connection.ConnectionData;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class SmartAttendance extends JFrame {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private JPanel panel;
    private JPanel attendancePanel;
    private JTable table;
    private CascadeClassifier faceDetector;
    private VideoCapture camera;
    private List<Mat> knownImages;
    private List<File> imageFiles;
    private BufferedImage image;
    private JPanel videoPanel, tablePanel;

    public SmartAttendance() {
        setTitle("Smart Attendance System");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(1100, 700);
        setLayout(null);
        setIconImage(new ImageIcon("icon/main_logo.png").getImage());
        setLocationRelativeTo(null);
        setResizable(false);


        String imagePath = "C:\\Users\\Sumon\\Unniversity Project\\Univarsity Management System\\image";
        knownImages = LoadImage.loadImagesFromFolder(imagePath);
        imageFiles = LoadImage.getImageFilesFromFolder(imagePath);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, 500, 400, null);
                }
            }
        };

        videoPanel = new JPanel();
        videoPanel.setBounds(0, 130, 500, 400);
        videoPanel.setLayout(new BorderLayout());
        videoPanel.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Smart Attendance System");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(60, 15, 350, 40);
        add(label);

        tablePanel = new JPanel();
        tablePanel.setBounds(500, 0, 590, 700);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        add(tablePanel);


        add(videoPanel);
        this.getContentPane().setBackground(new Color(202, 201, 201));


        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        String xmlFile = "C:\\Users\\Sumon\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_default.xml";
        faceDetector = new CascadeClassifier(xmlFile);
        camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(this, "Error: Camera not found!", "Camera Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        generateTable();
        startDetection();
        setVisible(true);
    }

    public void startDetection() {
        new Thread(() -> {
            Mat frameMat = new Mat();
            while (camera.read(frameMat)) {
                MatOfRect faces = new MatOfRect();
                faceDetector.detectMultiScale(frameMat, faces);

                for (Rect rect : faces.toArray()) {
                    Imgproc.rectangle(frameMat, new Point(rect.x, rect.y),
                            new Point(rect.x + rect.width, rect.y + rect.height),
                            new Scalar(100, 200, 204), 2);

                    for (int i = 0; i < knownImages.size(); i++) {
                        if (compareImages(frameMat, knownImages.get(i))) {
                            String matchingFilePath = imageFiles.get(i).getPath();
                            String[] name = matchingFilePath.split("\\\\");

                            int id = checkValidity(name[name.length - 1]);

                            Imgproc.putText(frameMat, "Match found", new Point(rect.x, rect.y - 10),
                                    Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 1);
                            break;
                        } else {
                            Imgproc.putText(frameMat, "Not Match!!!", new Point(rect.x, rect.y - 10),
                                    Imgproc.FONT_HERSHEY_COMPLEX_SMALL, 0.5, new Scalar(0, 0, 255), 1);
                            break;
                        }
                    }
                }

                SwingUtilities.invokeLater(() -> {
                    image = matToBufferedImage(frameMat);
                    panel.repaint();
                });

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private boolean compareImages(Mat frame, Mat knownImage) {
        Mat diff = new Mat();
        Imgproc.matchTemplate(frame, knownImage, diff, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(diff);

        return mmr.maxVal > 0.3;
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int type = (mat.channels() > 1) ? BufferedImage.TYPE_3BYTE_BGR : BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage img = new BufferedImage(mat.cols(), mat.rows(), type);
        mat.get(0, 0, ((DataBufferByte) img.getRaster().getDataBuffer()).getData());
        return img;
    }

    public int checkValidity(String namePath) {
        int id = 0;
        try {
            ConnectionData connection = new ConnectionData();
            String query = "SELECT * FROM university.student_info WHERE image = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, namePath);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                id = set.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void main(String[] args) {
        new SmartAttendance();
    }

    public void generateTable() {
        ConnectionData connection = new ConnectionData();
        try {
            String query = "SELECT * FROM university.student_info";
            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(query);
            String[] columns = {"ID", "Regi No", "Name", "Date", "Time"};

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getInt("regi_no"));
                row.add(resultSet.getString("name"));

                data.add(row);
            }

            DefaultTableModel model = new DefaultTableModel(data, new Vector<>(Arrays.asList(columns)));
            table.setModel(model);

            table.revalidate();
            table.repaint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
