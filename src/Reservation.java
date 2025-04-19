public class Reservation {
    /*
This class contains important information needed in order to reserve a room.
@E.Sanchez
@version 1.0
 */

    private String checkInDate;
    private String checkOutDate;
    private int revId;
    private int userId;
    private String reserveTime;
    /*
    @param checkInDate - check in date
    @param checkOutDate - check out date
    @param revId - reservation ID
    @param userId  - userID
    @param reserveTime - reservation time
     */
    /*
    setters
     */

    public void setCheckInDate(String checkInDate) {this.checkInDate = checkInDate;}
    public void setCheckOutDate(String checkOutDate) {this.checkOutDate = checkOutDate;}
    public void setRevId(int revId) {this.revId = revId;}
    public void setUserId(int userId) {this.userId = userId;}
    public void setReserveTime(String reserveTime) {this.reserveTime = reserveTime;}
     /*
    getters
     */

    public String getCheckInDate() {return checkInDate;}

    public String getCheckOutDate() {return checkOutDate;}

    public int getRevId() {return revId;}

    public int getUserId() {return userId;}

    public String getReserveTime() {return reserveTime;}





}
