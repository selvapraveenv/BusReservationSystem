import java.util.*;

public class BusDemo {
    public static void main(String[] args) throws Exception {
        BusDAO busInfo = new BusDAO();
        busInfo.displayBusInfo();
        

        int userOpt = 1;
        Scanner scanner = new Scanner(System.in);
        try{
            while(userOpt==1){
                System.out.print("Enter 1 to Book and 2 to Exit:");
                if(scanner.hasNextInt()){
                    userOpt = scanner.nextInt();
                }
                if(userOpt==1){
                    System.out.println("Booking..");
                    Booking curBooking = new Booking();
                    if(curBooking.isAvailable()){
                        BookingDAO bookingDAO = new BookingDAO();
                        bookingDAO.addBooking(curBooking);
                        System.out.println("Ticket Booked Successfully. Have a safer journey.");
                    }
                    else{
                        System.out.println("Sorry, Bookings full");
                    }
                }
                else{
                    System.out.println("Thank you! Visit again.");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            scanner.close();
        }
    }
}