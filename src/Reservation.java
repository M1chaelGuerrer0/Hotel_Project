import java.sql.Date;
import java.sql.Time;

public class Reservation {
    /*
This class contains important information needed in order to reserve a room.
@E.Sanchez
@version 1.0
 */
    private int reserve_id;
    private int room_Number;
    private int guest_id;
    private String name;
    private Date check_In_Date;
    private Date check_Out_Date;
    private Time check_In_Time;
    private Time check_Out_Time;
    private int card_id;

    public Reservation(){}
    public Reservation(int reserve_id,int room_Number,int guest_id,String name,
                       Date check_In_Date,Date check_Out_Date,Time check_In_Time,
                       Time check_Out_Time, int card_id){
        this.reserve_id = reserve_id;
        this.room_Number = room_Number;
        this.guest_id = guest_id;
        this.name = name;
        this.check_In_Date = check_In_Date;
        this.check_Out_Date = check_Out_Date;
        this.check_In_Time = check_In_Time;
        this.check_Out_Time = check_Out_Time;
        this.card_id = card_id;
    }

    // setters
    public void setRoom_Number(int room_Number) {this.room_Number = room_Number;}
    public void setGuest_id(int guest_id) {this.guest_id = guest_id;}
    public void setName(String name) {this.name = name;}
    public void setCheck_In_Date(Date check_In_Date) {this.check_In_Date = check_In_Date;}
    public void setCheck_Out_Date(Date check_Out_Date) {this.check_Out_Date = check_Out_Date;}
    public void setCheck_In_Time(Time check_In_Time) {this.check_In_Time = check_In_Time;}
    public void setCheck_Out_Time(Time check_Out_Time) {this.check_Out_Time = check_Out_Time;}
    public void setReserve_id(int reserve_id) {this.reserve_id = reserve_id;}
    public void setCard_id(int card_id) {this.card_id = card_id;}

    // getters
    public int getRoom_Number() {return room_Number;}
    public int getGuest_id() {return guest_id;}
    public String getName() {return name;}
    public Date getCheck_In_Date() {return check_In_Date;}
    public Date getCheck_Out_Date() {return check_Out_Date;}
    public Time getCheck_In_Time() {return check_In_Time;}
    public Time getCheck_Out_Time() {return check_Out_Time;}
    public int getReserve_id() {return reserve_id;}
    public int getCard_id() {return card_id;}

    @Override
    public String toString() {
        return "\nreserve_id: " + this.reserve_id +
                "\nroom_Number: " + this.room_Number +
                "\nguest_id: " + this.guest_id +
                "\nname: "+ this.name +
                "\ncheck_In_Date: " + this.check_In_Date +
                "\ncheck_Out_Date: " + this.check_Out_Date +
                "\ncheck_In_Time: " + this.check_In_Time +
                "\ncheck_Out_Time: " + this.check_Out_Time +
                "\ncard_id: " + this.card_id + "\n";
    }
}
