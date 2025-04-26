
/*
    room reservation class
    4/18/25
    @author Mirella soto

    This is a temporary class that currently only holds an example list.
*/


public class roomres {

    private String name;
    private String rooms;
    private String checkInDate;
    private String checkInTime;
    private String checkOutDate;
    private String checkOutTime;
    private String revenue;

    public String getName() {
        return name;
    }

    public String getRooms() {
        return rooms;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }


    // a temporary list used for testing the check-in/ check-out scene
    public static ObservableList<roomres> getInitialList() {
        roomres p1 = new roomres("John","room 101", "4/10/25", "4/20/25","$3500");
        roomres p2 = new roomres("Mary","room 102", "4/11/25", "4/1/25","$3500");
        roomres p3 = new roomres("Joe","room 103", "4/11/25", "4/33/25","$70000");
        roomres p4 = new roomres("Ben","room 104", "4/12/25", "4/21/25","$3500");
        roomres p5 = new roomres("Wendy", "room 105", "4/12/25", "4/25/25","$3500");
        roomres p6 = new roomres("Robin", "room 106", "4/12/25", "4/20/25","$3500");
        roomres p7 = new roomres("William","room 107", "4/13/25", "4/14/25","$350");
        roomres p8 = new roomres("Kevin","room 108", "4/13/25", "4/19/25","$1750");
        return FXCollections.observableArrayList(p1, p2, p3, p4,p5, p6, p7, p8);
    }

    //constructor
    public roomres(String name, String rooms, String checkInDate, String checkOutDate, String revenue) {
        this.name = name;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.revenue = revenue;

    }
}


