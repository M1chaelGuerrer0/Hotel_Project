
import java.sql.*;

public class HotelDemo {
    public static void main(String[] args) {
        // the registration process
        // STEP 1: new Guest
        User guest = new User("Simon","Claw","TheClaw@gmail.com","123",
                "xxxxxxxxxxx","Knowwhere","", "xxxxx","LA","CA",
                "US",0);
        // running it the first time will grant the user_id
        // MAKE BELOW A COMMENT AFTER FIRST SEQUENCE IF FIELDS ARE THE SAME IN USER
        HotelDataBase.addGuest(guest); // add guest to database


        // STEP 2: new Card for guest
        // UNCOMMENT BELOW IF THIS IS NOT THE FIRST SEQUENCE WITH THE SAME GUEST FIELDS
        //guest = HotelDataBase.getGuest(guest.getEmail()); // this is to get the user.id
        Card card = new Card(guest.getFirst_Name()+" "+guest.getLast_Name(),"1111111111111111",
                "1027","333",guest.getAddress1(), "", guest.getCity(), guest.getCountry(), guest.getState(),
                guest.getZip_Code(), 0, guest.getUser_id());
        // running it the first time will grant the card_id
        // MAKE BELOW A COMMENT AFTER FIRST SEQUENCE WITH SAME FIELDS IN CARD
        HotelDataBase.addCard(card); // adds card to db
        /* TO UPDATE CARD:
         // If we are doing multiple cards per guest
         HotelDataBase.displayCards(guest.getUser_id()); // display all cards associated with guest
         card.setCard_id(4); // chose which id to update
         // If 1-1 with 1 card per 1 guest
         card.setCard_id(guest.getUser_id()); // same as guest_id
         If 1-1 deleting card can lead to issues
        */

        /* Card Methods: HotelDataBase
         * .addCard(Card card) // adds a card to db
         * .updateCard(Card card) // updates a card to db
         * .displayCards(int guest_id) // displays all cards associated to guest_id
         * .getCard(int card_id) // copies the card from the db into a Card
         * .deleteCard(int card_id) // deletes a card from the db
         */

        // Room Registration
        Room room = new Room();
        room.setRoom_Type("AC");
        room.setPrice_Per_Night(350.00);
        room.setRoom_Capacity(2);
        room.setAvailability("OPEN");
        // running it the first time will grant the room_Number
        // there is nothing unique about rooms so dupes can happen
        // MAKE BELOW A COMMENT IF AFTER FIRST SEQUENCE TO PREVENT DUPES
        //HotelDataBase.addRoom(room); // adds room to db
        /* Room Methods: HotelDataBase
         * .addRoom(Room room) // adds a room to the db
         * .updateRoom(Room room) // updates a room to the db
         * .displayRoom() // displays all rooms
         * .getRoom(int room_Number) // copies a room from the db into a Room
         * .deleteCard(int room_Number) // deletes a room from the db
         */

        // reservation process
        // UNCOMMENT THE TWO BELOW IF THIS IS NOT THE FIRST SEQUENCE OF ADDING THE SAME FIELDS
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