public class HotelApp {
    public static void main(String[] args) {
        User user = new User();
        user.setFirst("Lamar");
        user.setLast("Davis");
        user.setEmail("GreenLD@gmail.com");
        user.setPassword("IHeartChop");
        user.setPhoneNumber("9114206969");
        user.setAddress1("11111 Strawberry St");
        user.setAddress2("");
        user.setCity("LS");
        user.setCountry("US");
        user.setState("CA");
        user.setZipCode("90001");
        user.setCardId(1);
        user.setUserId(1);
        user.displayUser();
        //HotelDataBase.addGuest(user);
        HotelDataBase.updateGuest(user);
    }
}