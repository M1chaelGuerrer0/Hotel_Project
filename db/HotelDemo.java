public class HotelDemo {
    public static void main(String[] args) {

        // the registration process
        // STEP 1: new Guest
        User guest = new User();
        guest.setFirst("Simon");
        guest.setLast("Claw");
        guest.setPhoneNumber("xxxxxxxxxxx");
        guest.setEmail("TheClaw@gmail.com");
        guest.setAddress1("Knowwhere");
        guest.setCity("DA");
        guest.setState("FL");
        guest.setZipCode("xxxxx");
        guest.setCountry("US");
        guest.setPassword("123");
        HotelDataBase.addGuest(guest); // add guest to database

        /* Guest Methods: HotelDataBase
        * .addGuest(User guest) // add guest in db
        * .updateGuest(User guest) // update guest in db
        * .getGuest(String email) // get User object from guest table in db
        * .deleteGuest(String email) // delete guest and cards associated too
        */

        // STEP 2: new Card for guest
        guest = HotelDataBase.getGuest(guest.getEmail()); // gets user_id
        Card card = new Card();
        card.setGuest_id(guest.getUser_id());
        card.setHolderName(guest.getFirst()+" "+guest.getLast());
        card.setCardNumber("1111111111111111");
        card.setExpiration("1027");
        card.setCvc("333");
        card.setAddress1(guest.getAddress1());
        card.setCity(guest.getCity());
        card.setCountry(guest.getCountry());
        card.setState(guest.getState());
        card.setZipCode(guest.getZipCode());
        HotelDataBase.addCard(card); // adds card to database

        /* TO UPDATE CARD:

         // If we are doing multiple cards per guest
         HotelDataBase.displayCards(guest.getUser_id()); // display all cards associated with guest
         card.setCard_id(4); // chose which id to update

         // If 1-1 with 1 card per 1 guest
         card.setCard_id(guest.getUser_id()); // same as guest_id
         If 1-1 deleting card can lead to issues

         // Updating Process:

         card.setCardNumber("wwwwwwwwwwwwwwww");
         card.setExpiration("wwww");
         card.setCvc("www");
         card.setZipCode("wwwww");
         HotelDataBase.updateCard(card);
        */

        /* Card Methods: HotelDataBase
         * .addCard(Card card)
         * .updateCard(Card card)
         * .displayCards(int guest_id)
         * .getCard(int card_id)
         * .deleteCard(int card_id)
         */

        /*
         Guest Examples:

         User guest = new User();
         guest.setFirst("John");
         guest.setLast("Pork");
         guest.setPhoneNumber("xxxxxxxxxxx");
         guest.setEmail("JohnPork@hotmail.com");
         guest.setAddress1("11111 xxxxx xx");
         guest.setCity("LA");
         guest.setState("CA");
         guest.setZipCode("11111");
         guest.setCountry("US");
         guest.setPassword("123");

         User guest = new User();
         guest.setFirst("John");
         guest.setLast("Pork");
         guest.setPhoneNumber("xxxxxxxxxxx");
         guest.setEmail("JohnPork@hotmail.com");
         guest.setAddress1("11111 xxxxx xx");
         guest.setCity("LA");
         guest.setState("CA");
         guest.setZipCode("11111");
         guest.setCountry("US");
         guest.setPassword("123");

         User guest2 = new User();
         guest2.setFirst("Bob");
         guest2.setLast("Bacon");
         guest2.setPhoneNumber("xxxxxxxxxxx");
         guest2.setEmail("BobBacon@rockemail.com");
         guest2.setAddress1("11112 xxxxx xx");
         guest2.setCity("LA");
         guest2.setState("CA");
         guest2.setZipCode("11111");
         guest2.setCountry("US");
         guest2.setPassword("B0B");

         Card Examples:

         guest = HotelDatabase.getGuest(guest.getEmail()); // gets user_id

         Card card = new Card();
         card.setGuest_id(guest.getUser_id());
         card.setHolderName(guest.getFirst()+" "+guest.getLast());
         card.setCardNumber("xxxxxxxxxxxxxxxx");
         card.setExpiration("xxxx");
         card.setCvc("xxx");
         card.setAddress1(guest.getAddress1());
         card.setCity(guest.getCity());
         card.setCountry(guest.getCountry());
         card.setState(guest.getState());
         card.setZipCode(guest.getZipCode());

         Card card = new Card();
         card.setGuest_id(guest.getUser_id());
         card.setHolderName("Tim Cheese");
         card.setCardNumber("yyyyyyyyyyyyyyyy");
         card.setExpiration("yyyy");
         card.setCvc("yyy");
         card.setAddress1("41 Slum");
         card.setCity("NY");
         card.setCountry("USA");
         card.setState("NY");
         card.setZipCode("91831");

         Card card = new Card();
         card.setGuest_id(guest.getUser_id());
         card.setHolderName(guest.getFirst()+" "+guest.getLast());
         card.setCardNumber("zzzzzzzzzzzzzzzz");
         card.setExpiration("zzzz");
         card.setCvc("zzz");
         card.setAddress1(guest.getAddress1());
         card.setCity(guest.getCity());
         card.setCountry(guest.getCountry());
         card.setState(guest.getState());
         card.setZipCode(guest.getZipCode());
        */
    }
}