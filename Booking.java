import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Booking {
    int BusNo;
    String passengerName;
    Date date;

    Booking(){
        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Enter Passenger Name:");
                passengerName = scanner.next();
                System.out.println("Enter Bus No:");
                BusNo = scanner.nextInt();
                System.out.println("Enter date(dd-MM-yyyy):");
                String dateInp = scanner.next();
                SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
                try{
                    date = simpleDate.parse(dateInp);
                }
                catch(ParseException e){
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e);
        }
    }

    public boolean isAvailable() throws Exception {
        BusDAO busDAO = new BusDAO();
        BookingDAO bookDao = new BookingDAO();

        int capacity = busDAO.getCapacity(BusNo);
        int booked = bookDao.getBookCount(BusNo,date);
        
        return booked<capacity;
    }
}
