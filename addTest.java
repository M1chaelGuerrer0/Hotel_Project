import java.util.List;
// add data to db test
// tables need to be empty
public class addTest {
    public static void main(String[] args){
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

        Card card = new Card();
        card.setHolder_Name(guest.getFirst_Name()+" "+guest.getLast_Name());
        card.setGuest_id(guest.getUser_id());
        card.setCard_Number("xxxxxxxxxxxxxxxx");
        card.setExpiration("xxxx");
        card.setCvc("xxx");
        card.setAddress1(guest.getAddress1());
        card.setCity(guest.getCity());
        card.setCountry(guest.getCountry());
        card.setState(guest.getState());
        card.setZip_Code(guest.getZip_Code());
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
}
