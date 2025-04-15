public class Room {
    private int room_id;
    private String roomType;
    private int  pricePerNight;
    private int roomCapacity;
    private String availability;
    private int userId;


    public void setRoomNumber(int roomNumber){this.room_id=roomNumber;}
    public void setRoomType(String roomType){this.roomType = roomType;}
    public void setPricePerNight(int pricePerNight){this.pricePerNight = pricePerNight;}
    public void setRoomCapacity(int roomCapacity){this.roomCapacity = roomCapacity;}
    public void setAvailability(String availability){this.availability = availability;}
    public void setUserId(int userId){this.userId = userId;}

    public int getRoomNumber(){return room_id;}
    public String getRoomType(){return roomType;}
    public int getPricePerNight(){return pricePerNight;}
    public int getRoomCapacity(){return roomCapacity;}
    public String getAvailability(){return availability;}
    public int getUserId(){return userId;}







}
