public class Room {
/*
This class contains information relevant to the hotel rooms.
@E.Sanchez
@version 1.0
 */

    private int roomNumber;
    private String roomType;
    private int  pricePerNight;
    private int roomCapacity;
    private String availability;
    private int userId;

/*
Setter methods
@param roomNumber  Integer - it counts how many rooms are in the hotel
@param roomType String - different room types
@param pricePerNight Integer - price per night of rooms
@param roomCapacity Integer - room capacity for the rooms
@param availability String  - availability for rooms
@param userId Integer - user ID of the user
 */
    public void setRoomNumber(int roomNumber){this.roomNumber=roomNumber;}

    public void setRoomType(String roomType){this.roomType = roomType;}

    public void setPricePerNight(int pricePerNight){this.pricePerNight = pricePerNight;}
    public void setRoomCapacity(int roomCapacity){this.roomCapacity = roomCapacity;}
    public void setAvailability(String availability){this.availability = availability;}
    public void setUserId(int userId){this.userId = userId;}

    /*
    getter methods
     */

    public int getRoomNumber(){return roomNumber;}
    public String getRoomType(){return roomType;}
    public int getPricePerNight(){return pricePerNight;}
    public int getRoomCapacity(){return roomCapacity;}
    public String getAvailability(){return availability;}
    public int getUserId(){return userId;}



}
