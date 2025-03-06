public class Room_Info {
    private int roomNum;
    private String availability;
    private String userID;
    private String date;
    private String checkIn;
    private String checkOut;

    public Room_Info(int roomNum, String availability, String userID, String date, String checkIn, String checkOut) {
        setRoomNum(roomNum);
        setAvailability(availability);
        setUserID(userID);
        setDate(date);
        setCheckIn(checkIn);
        setCheckOut(checkOut);
    }

    ////////// setters //////////
    public void setRoomNum(int roomNum) {
        this.roomNum=roomNum;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
    ////////// getters //////////
    public String getAvailability() {
        return availability;
    }
    public int getRoomNum() {
        return roomNum;
    }
    public String getUserID() {
        return userID;
    }
    public String getDate() {
        return date;
    }
    public String getCheckIn() {
        return checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
}
