public class User_Info {
    private int userID;
    private String first;
    private String last;
    private String cardID;

    public User_Info(int userID, String first, String last, String cardID) {
        setUserID(userID);
        setFirst(first);
        setLast(last);
        setCardID(cardID);
    }
    ///////// Setters /////////
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setFirst(String first) {
        this.first = first;
    }
    public void setLast(String last) {
        this.last = last;
    }
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
    ///////// Getters /////////
    public int getUserID() {
        return userID;
    }
    public String getFirst() {
        return first;
    }
    public String getLast() {
        return last;
    }
    public String getCardID() {
        return cardID;
    }
}
