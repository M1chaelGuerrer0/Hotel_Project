public class Reservation {
    private String checkInDate;
    private String checkOutDate;
    private int revId;
    private int userId;
    private String reserveTime;



    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setRevId(int revId) {
        this.revId = revId;
    }

    public int getRevId() {
        return revId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getReserveTime() {
        return reserveTime;
    }
}
