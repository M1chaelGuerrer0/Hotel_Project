public class HotelApp {
    public static void main(String[] args) {
        // Add a new guest
        System.out.println("add test"); GuestManager.addGuest("Juan Carlos", "JuanCarlos1@yahoo.com", "911-420-6969");
        // Update a guest
        //System.out.println("update test"); GuestManager.updateGuest(2, "Johnathan Pork", "john.Pork@example.com", "123-456-7890");
        // delete test
        //System.out.println("delete test"); GuestManager.deleteGuest(1);
        GuestManager.displayAllGuests();
    }
}