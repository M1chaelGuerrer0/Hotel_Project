
import java.sql.*;

public class HotelDemo {
    public static void main(String[] args) {
        // the registration process
        // STEP 1: new Guest
        User guest = new User("Simon","Claw","TheClaw@gmail.com","123",
                "xxxxxxxxxxx","Knowwhere","", "xxxxx","LA","CA",
                "US",0);
        HotelDataBase.addGuest(guest); // add guest to database
        // STEP 2: new Card for guest
        // UNCOMMENT BELOW TO GET guest_id after first run
        //guest = HotelDataBase.getGuest(guest.getEmail()); // this is to get the user.id
        Card card = new Card(guest.getFirst_Name()+" "+guest.getLast_Name(),"1111111111111111",
                "1027","333",guest.getAddress1(), "", guest.getCity(), guest.getCountry(), guest.getState(),
                guest.getZip_Code(), 0, guest.getUser_id());
        HotelDataBase.addCard(card); // adds card to db

        // Room Registration
        Room room = new Room();
        room.setRoom_Type("AC");
        room.setPrice_Per_Night(350.00);
        room.setRoom_Capacity(2);
        room.setAvailability("OPEN");
        // running it the first time will grant the room_Number
        // there is nothing unique about rooms so dupes can happen
        // MAKE BELOW A COMMENT IF AFTER FIRST SEQUENCE TO PREVENT DUPES
        HotelDataBase.addRoom(room);

        // reservation process
        // UNCOMMENT THE TWO BELOW AFTER FIRST RUN
        //card = HotelDataBase.getCard(1); // this gives the card_id
        //room = HotelDataBase.getRoom(1); // this gives the room_Number
        Reservation reservation = new Reservation();
        reservation.setRoom_Number(room.getRoom_Number());
        reservation.setGuest_id(guest.getUser_id());
        reservation.setCard_id(card.getCard_id());
        reservation.setName(guest.getFirst_Name() + " " + guest.getLast_Name());
        reservation.setCheck_In_Date(Date.valueOf("2025-12-24")); // FORMAT IS IMPORTANT
        reservation.setCheck_Out_Date(Date.valueOf("2025-12-26")); // FORMAT IS IMPORTANT

        System.out.println("\nPREP FOR RESERVATION:(if any 0's then failure)\n\tguest_id = "+guest.getUser_id()+"\n\tcard_id = "+card.getCard_id()+"\n\troom_Number = "+room.getRoom_Number());
        // running it the first time will grant the reserve_id
        // MAKE BELOW A COMMENT IF THIS IS NOT THE FIRST SEQUENCE WITH THE SAME FIELDS
        HotelDataBase.addReservation(reservation); // adds reservation to db
        // UNCOMMENT BELOW IF THIS IS NOT THE FIRST SEQUENCE WITH SAME FIELDS
        //reservation = HotelDataBase.getReservation(room.getRoom_Number()); // gets reserve_id
        //reservation.setCheck_In_Time(Time.valueOf("12:00:00")); // FORMAT IS IMPORTANT
        //reservation.setCheck_Out_Time(Time.valueOf("11:00:00")); // FORMAT IS IMPORTANT
        //HotelDataBase.updateReservation(reservation);

    }
}