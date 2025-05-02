package Application;

import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class addTest {

    @Test
    public void addGuestTest(){
        User guest = new User();
        guest.setFirst_Name("x");
        guest.setLast_Name("x");
        guest.setEmail("x@gmail.com");
        guest.setPassword("abc");
        guest.setPhone_Number("xxxxxxxxxxx");
        guest.setAddress1("x");
        guest.setCity("LA");
        guest.setState("CA");
        guest.setZip_Code("xxxxx");
        guest.setCountry("US");
        System.out.println("Parameters for Guest:"+guest);
        HotelDataBase.addGuest(guest); // first time running will have user_id
        List<User> guests = HotelDataBase.getGuests();
        System.out.println("Add Guest Test Result:\nExpected: [\n" +
                "first_name: x\n" +
                "last_name: x\n" +
                "email: x@gmail.com\n" +
                "password: abc\n" +
                "phone_Number: xxxxxxxxxxx\n" +
                "address1: x\n" +
                "address2: \n" +
                "zip_Code: xxxxx\n" +
                "city: LA\n" +
                "state: CA\n" +
                "country: US\n" +
                "user_id: 1\n" +
                "]\nObserved: "+guests);
    }
    @Test
    public void addCardTest(){
        Card card = new Card();
        card.setHolder_Name("x x");
        card.setGuest_id(1);
        card.setCard_Number("xxxxxxxxxxxxxxxx");
        card.setExpiration("xxxx");
        card.setCvc("xxx");
        card.setAddress1("x");
        card.setCity("LA");
        card.setCountry("US");
        card.setState("CA");
        card.setZip_Code("x");
        System.out.println("\nParameters for Card:"+card);
        HotelDataBase.addCard(card); // first time running will have card_id
        List<Card> cards = HotelDataBase.getCards();
        System.out.println("Add Card Test Result:\nExpected: [\n" +
                "holder_Name: x x\n" +
                "card_Number: xxxxxxxxxxxxxxxx\n" +
                "expiration: xxxx\n" +
                "cvc: xxx\n" +
                "address1: x\n" +
                "address2: \n" +
                "city: xxxxx\n" +
                "country: LA\n" +
                "state: CA\n" +
                "zip_Code: US\n" +
                "card_id: 1\n" +
                "guest_id: 1\n" +
                "]\nObserved: "+cards);
    }
    @Test
    public void addRoomTest() {
        Room room = new Room(0,"AC",350.00,2,"OPEN");
        System.out.println("\nParameters for Room:"+room);
        HotelDataBase.addRoom(room); // will keep duping
        List<Room> rooms  = HotelDataBase.getRooms();
        System.out.println("Add Reservation Results:\nExpected: [\n" +
                "room_Number: 1\n" +
                "room_Type: AC\n" +
                "price_Per_Night: 350.0\n" +
                "room_Capacity: 2\n" +
                "availability: OPEN\n]"+ "\nObserved: " + rooms);
    }
    @Test
    public void addResTest() {
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
