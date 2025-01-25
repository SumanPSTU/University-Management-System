package attendenceSystem;

import com.connection.ConnectionData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetAttendance {
    private int attendanceID;
    public SetAttendance(int attendanceID) {
        this.attendanceID = attendanceID;
    }


    public void attendance() {
        ConnectionData connection = new ConnectionData();
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        String query = "UPDATE attendance SET formattedDate = 'present',  WHERE id = ?";
        try {


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
