import java.sql.Date;
import java.util.List;

// addTest needs to be run first once
public class ResTest {
    public static void main(String[] args) {
        Reservation reservation = new Reservation();
        reservation.setRoom_Number(1);
        reservation.setGuest_id(1);
        reservation.setCard_id(1);
        reservation.setName("x");
        reservation.setCheck_In_Date(Date.valueOf("2025-12-24"));
        reservation.setCheck_Out_Date(Date.valueOf("2025-12-25"));
        System.out.println("Parameters for Reservation:\n"+reservation);
        HotelDataBase.addReservation(reservation);
        List<Reservation> reservations = HotelDataBase.getReservations();
        System.out.println("Add Reservation Test Result:\nExpected: [\n" +
                "reserve_id: 1\n" +
                "room_Number: 1\n" +
                "guest_id: 1\n" +
                "name: x\n" +
                "check_In_Date: 2025-12-24\n" +
                "check_Out_Date: 2025-12-25\n" +
                "check_In_Time: 00:00:00\n" +
                "check_Out_Time: 00:00:00\n" +
                "card_id: 1\n" +
                "]" + "\nObserved: " + reservations);
    }
}
