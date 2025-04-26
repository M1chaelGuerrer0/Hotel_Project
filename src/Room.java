public class Room {
    private int room_Number;
    private String room_Type;
    private double  price_Per_Night;
    private int room_Capacity;
    private String availability;

    // Setters
    public void setRoom_Type(String roomType){this.room_Type = roomType;}
    public void setPrice_Per_Night(double price_Per_Night){this.price_Per_Night = price_Per_Night;}
    public void setRoom_Capacity(int room_Capacity){this.room_Capacity = room_Capacity;}
    public void setAvailability(String availability){this.availability = availability;}
    public void setRoom_Number(int room_Number){this.room_Number = room_Number;};

    // Getters
    public String getRoom_Type(){return room_Type;}
    public double getPrice_Per_Night(){return price_Per_Night;}
    public int getRoom_Capacity(){return room_Capacity;}
    public String getAvailability(){return availability;}
    public int getRoom_Number() {return room_Number;}

}
