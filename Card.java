public class Card {
    private int cardID;
    private String cardNumber;
    private String expiration;
    private String cvc;

    public Card(int cardID, String cardNumber, String expiration, String cvc) {
        setCardID(cardID);
        setCardNumber(cardNumber);
        setExpiration(expiration);
        setCvc(cvc);
    }
    /////// Setters ///////
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    /////// Getters ///////
    public int getCardID() {
        return cardID;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getExpiration() {
        return expiration;
    }
    public String getCvc() {
        return cvc;
    }
}
