package Application;

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
        Room room = new Room(0,"AC",350.00,2,"OPEN");
        // running it the first time will grant the room_Number
        // there is nothing unique about rooms so dupes can happen
        // MAKE BELOW A COMMENT IF AFTER FIRST SEQUENCE TO PREVENT DUPES
        HotelDataBase.addRoom(room);

        // reservation process
        // UNCOMMENT THE TWO BELOW AFTER FIRST RUN
        //card = HotelDataBase.getCard(1); // this gives the card_id
        //room = HotelDataBase.getRoom(1); // this gives the room_Number
        Reservation reservation = new Reservation(0,room.getRoom_Number(),guest.getUser_id(),
                guest.getFirst_Name(), Date.valueOf("2025-12-24"), Date.valueOf("2025-12-26"), // format
                Time.valueOf("00:00:00"), Time.valueOf("00:00:00"),card.getCard_id()); // format
        System.out.println("\nPREP FOR RESERVATION:(if any 0's then failure)\n\tguest_id = "+guest.getUser_id()+"\n\tcard_id = "+card.getCard_id()+"\n\troom_Number = "+room.getRoom_Number());
        // running it the first time will grant the reserve_id
        // MAKE BELOW A COMMENT IF THIS IS NOT THE FIRST SEQUENCE WITH THE SAME FIELDS
        HotelDataBase.addReservation(reservation); // adds reservation to db
        System.out.println("\nTotal Revenue: $" + HotelDataBase.total_revenue());
    }
}