/*
    HotelDemo is a testing place for the methods in HotelDataBase.
    This file is to be removed when a working prototype version is in place.

    @author Michael Guerrero
    @version 1.0
*/

public class HotelDemo {
    public static void main(String[] args) {
        User guest = new User();
        Card card = new Card();

        // guest
        guest.setFirst("Lamar");
        guest.setLast("Davis");
        guest.setEmail("GreenLD@gmail.com");
        guest.setPassword("IHeartChop");
        guest.setPhoneNumber("9114206969");
        guest.setAddress1("11111 Strawberry St");
        guest.setAddress2("");
        guest.setCity("LS");
        guest.setCountry("US");
        guest.setState("CA");
        guest.setZipCode("90001");
        // guest.displayUser(); // display current guest

        // card
        card.setHolderName(guest.getFirst()+" "+guest.getLast());
        card.setCardNumber("xxxxxxxxxx");
        card.setExpiration("xx/xx");
        card.setCvc("xxx");
        card.setCity(guest.getCity());
        card.setCountry(guest.getCountry());
        card.setAddress1(guest.getAddress1());
        card.setAddress2(guest.getAddress2());
        card.setState(guest.getState());
        card.setZipCode(guest.getZipCode());


        // HotelDataBase.addGuest(guest);
        // HotelDataBase.updateGuest(guest);
        // HotelDataBase.deleteGuest(1);
        // HotelDataBase.addCard(card);
    }
}