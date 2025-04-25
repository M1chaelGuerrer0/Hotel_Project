public class HotelDemo {
    public static void main(String[] args) {
        /* Guest
        User guest = new User();
        guest.setFirst("John");
        guest.setLast("Pork");
        guest.setPhoneNumber("xxxxxxxxxxx");
        guest.setEmail("johnnyPorker@hotmail.com");
        guest.setUserId(2);
        guest.setAddress1("11111 xxxxx xx");
        guest.setCity("LA");
        guest.setState("CA");
        guest.setZipCode("11111");
        guest.setCountry("US");
        guest.setPassword("123");
        */

        String email = "GreenLD@gmail.com";
        User guestExample = HotelDataBase.getGuest(email);
        String passwordExample = guestExample.getPassword();
        System.out.println(passwordExample);
        // HotelDataBase.addGuest(guest);
        // HotelDataBase.updateGuest(guest);
        // HotelDataBase.deleteGuest(1);
        // HotelDataBase.addCard(card);
    }
}