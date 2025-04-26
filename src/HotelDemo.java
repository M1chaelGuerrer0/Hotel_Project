
import java.sql.*;

public class HotelDemo {
    public static void main(String[] args) {

        // the registration process
        // STEP 1: new Guest
        User guest = new User();
        guest.setFirst_Name("Simon");
        guest.setLast_Name("Claw");
        guest.setPhone_Number("xxxxxxxxxxx");
        guest.setEmail("TheClaw@gmail.com");
        guest.setAddress1("Knowwhere");
        guest.setCity("DA");
        guest.setState("FL");
        guest.setZip_Code("xxxxx");
        guest.setCountry("US");
        guest.setPassword("123");
        // running it the first time will grant the user_id
        // MAKE BELOW A COMMENT AFTER FIRST SEQUENCE IF FIELDS ARE THE SAME IN USER
        HotelDataBase.addGuest(guest); // add guest to database

        System.out.println("guest_id:\nEXPECTED: anything > 0\nOBSERVED: "+ guest.getUser_id()+" > 0");

        /* Guest Methods: HotelDataBase
        * .addGuest(User guest) // add guest in db
        * .updateGuest(User guest) // update guest in db
        * .getGuest(String email) // copies a guest from the db into a User
        * .deleteGuest(String email) // delete guest and cards associated too
        * .displayGuest() // prints all guest in guest table in db
        */

        // STEP 2: new Card for guest
        // UNCOMMENT BELOW IF THIS IS NOT THE FIRST SEQUENCE WITH THE SAME GUEST FIELDS
        // guest = HotelDataBase.getGuest(guest.getEmail()); // this is to get the user.id
        Card card = new Card();
        card.setGuest_id(guest.getUser_id());
        card.setHolder_Name(guest.getFirst_Name()+" "+guest.getLast_Name());
        card.setCard_Number("1111111111111111");
        card.setExpiration("1027");
        card.setCvc("333");
        card.setAddress1(guest.getAddress1());
        card.setCity(guest.getCity());
        card.setCountry(guest.getCountry());
        card.setState(guest.getState());
        card.setZip_Code(guest.getZip_Code());
        // running it the first time will grant the card_id
        // MAKE BELOW A COMMENT AFTER FIRST SEQUENCE WITH SAME FIELDS IN CARD
        HotelDataBase.addCard(card); // adds card to db
        System.out.println("card_id:\nEXPECTED: anything > 0\nOBSERVED: "+card.getCard_id()+" > 0" );

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
        HotelDataBase.addRoom(room); // adds room to db
        System.out.println("room_Number:\nEXPECTED: anything > 0\nOBSERVED: "+room.getRoom_Number()+" > 0" );

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

        System.out.println("PREP FOR RESERVATION:\nguest_id = "+guest.getUser_id()+"\ncard_id = "+card.getCard_id()+"\nroom_Number = "+room.getRoom_Number());
        // running it the first time will grant the reserve_id
        // MAKE BELOW A COMMENT IF THIS IS NOT THE FIRST SEQUENCE WITH THE SAME FIELDS
        HotelDataBase.addReservation(reservation); // adds reservation to db
        System.out.println("reserve_id:\nEXPECTED: anything > 0\nOBSERVED: "+reservation.getReserve_id()+" > 0" );
        HotelDataBase.displayReservation();
        // UNCOMMENT BELOW IF THIS IS NOT THE FIRST SEQUENCE WITH SAME FIELDS
        //reservation = HotelDataBase.getReservation(room.getRoom_Number()); // gets reserve_id
        reservation.setCheck_In_Time(Time.valueOf("12:00:00")); // FORMAT IS IMPORTANT
        reservation.setCheck_Out_Time(Time.valueOf("11:00:00")); // FORMAT IS IMPORTANT

        HotelDataBase.updateReservation(reservation);
        HotelDataBase.displayReservation();




        /*
         Guest Examples:

         User guest = new User();
         guest.setFirst_Name("John");
         guest.setLast_Name("Pork");
         guest.setPhone_Number("xxxxxxxxxxx");
         guest.setEmail("JohnPork@hotmail.com");
         guest.setAddress1("11111 xxxxx xx");
         guest.setCity("LA");
         guest.setState("CA");
         guest.setZip_Code("11111");
         guest.setCountry("US");
         guest.setPassword("123");

         User guest = new User();
         guest.setFirst_Name("John");
         guest.setLast_Name("Pork");
         guest.setPhone_Number("xxxxxxxxxxx");
         guest.setEmail("JohnPork@hotmail.com");
         guest.setAddress1("11111 xxxxx xx");
         guest.setCity("LA");
         guest.setState("CA");
         guest.setZip_Code("11111");
         guest.setCountry("US");
         guest.setPassword("123");

         User guest2 = new User();
         guest2.setFirst_Name("Bob");
         guest2.setLast_Name("Bacon");
         guest2.setPhone_Number("xxxxxxxxxxx");
         guest2.setEmail("BobBacon@rockemail.com");
         guest2.setAddress1("11112 xxxxx xx");
         guest2.setCity("LA");
         guest2.setState("CA");
         guest2.setZip_Code("11111");
         guest2.setCountry("US");
         guest2.setPassword("B0B");

         Card Examples:

         guest = HotelDatabase.getGuest(guest.getEmail()); // gets user_id

         Card card = new Card();
         card.setGuest_id(guest.getUser_id());
         card.setHolder_Name(guest.getFirst_Name()+" "+guest.getLast_Name());
         card.setCard_Number("xxxxxxxxxxxxxxxx");
         card.setExpiration("xxxx");
         card.setCvc("xxx");
         card.setAddress1(guest.getAddress1());
         card.setCity(guest.getCity());
         card.setCountry(guest.getCountry());
         card.setState(guest.getState());
         card.setZip_Code(guest.getZip_Code());

         Card card = new Card();
         card.setGuest_id(guest.getUser_id());
         card.setHolder_Name("Tim Cheese");
         card.setCard_Number("yyyyyyyyyyyyyyyy");
         card.setExpiration("yyyy");
         card.setCvc("yyy");
         card.setAddress1("41 Slum");
         card.setCity("NY");
         card.setCountry("USA");
         card.setState("NY");
         card.setZip_Code("91831");
        */
    }
}