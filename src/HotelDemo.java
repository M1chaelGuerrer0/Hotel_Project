
import java.sql.*;
import java.util.List;

public class HotelDemo {
    public static void main(String[] args) {
        HotelDataBase.reset(); // NO LONGER HAVE TO UNCOMMENT OR MAKE COMMENTS
        // the registration process
        // STEP 1: new Guest
        User guest = new User("Simon","Claw","TheClaw@gmail.com","123",
                "xxxxxxxxxxx","Knowwhere","", "xxxxx","LA","CA",
                "US",0);
        HotelDataBase.addGuest(guest); // add guest to database
        // STEP 2: new Card for guest
        //guest = HotelDataBase.getGuest(guest.getEmail()); // this is to get the user.id and other fields
        Card card = new Card(guest.getFirst_Name()+" "+guest.getLast_Name(),"1111111111111111",
                "1027","333",guest.getAddress1(), "", guest.getCity(), guest.getCountry(), guest.getState(),
                guest.getZip_Code(), 0, guest.getUser_id());
        HotelDataBase.addCard(card); // adds card to db

        // Room Registration
        Room room = new Room(0,"AC",350.00,2,"OPEN");
        HotelDataBase.addRoom(room); // will dupe

        // reservation process
        //card = HotelDataBase.getCard(1); // this gives the card_id and other fields
        //room = HotelDataBase.getRoom(1); // this gives the room_Number and other fields
        Reservation reservation = new Reservation();
        reservation.setGuest_id(guest.getUser_id());
        reservation.setCard_id(card.getCard_id());
        reservation.setRoom_Number(room.getRoom_Number());
        reservation.setName("Godzilla");
        reservation.setCheck_In_Date(Date.valueOf("2025-12-24")); // format
        reservation.setCheck_Out_Date(Date.valueOf("2025-12-26")); // format
        reservation.setCheck_In_Time(Time.valueOf("00:00:00")); // format
        reservation.setCheck_Out_Time(Time.valueOf("00:00:00")); // format
        System.out.println("\nPREP FOR RESERVATION:(if any 0's then failure)\n\tguest_id = "+
                guest.getUser_id()+"\n\tcard_id = "+card.getCard_id()+"\n\troom_Number = "+room.getRoom_Number());
        HotelDataBase.addReservation(reservation); // current reservation to db
        reservation.setCheck_In_Date(Date.valueOf("2024-12-24")); // format
        reservation.setCheck_Out_Date(Date.valueOf("2024-12-26")); // format
        reservation.setCheck_In_Time(Time.valueOf("12:00:00")); // format
        reservation.setCheck_Out_Time(Time.valueOf("11:00:00")); // format
        HotelDataBase.addReservation(reservation); // past reservation to db
        HotelDataBase.deleteGuest(guest.getEmail()); // guest can be deleted and reservation will stay
        List<Reservation> reservationList = HotelDataBase.getReservations(); // current reservations
        List<Reservation> reservations = HotelDataBase.getReservationHistory(); // all reservations
        System.out.println("EVERY RESERVATION:"+reservations);
        System.out.println("\nCURRENT RESERVATIONS:" + reservationList);
        System.out.println("\nTotal Revenue: $" + HotelDataBase.total_revenue());
    }
}