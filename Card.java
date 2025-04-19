public class Card {
/*
This class contains the information about the customers card.
@E.Sanchez
@version 1.0
 */

    private int card_id;
    private String holderName;
    private String cardNumber;
    private String expiration;
    private String cvc;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String state;
    private String zipCode;

    /////// Setters ///////
    public void setCardID(int cardID) {
        this.card_id = cardID;
    }
    public void setHolderName(String holderName) {this.holderName = holderName;}
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    public void setAddress1(String address1) {this.address1 = address1;}
    public void setAddress2(String address2) {this.address2 = address2;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}
    public void setCountry(String country) {this.country = country;}
    /////// Getters ///////
    public int getCardID() {
        return card_id;
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
    public String getHolderName() {return holderName;}
    public String getAddress1() {return address1;}
    public String getAddress2() {return address2;}
    public String getCity() {return city;}
    public String getZipCode() {return zipCode;}
    public String getState() {return state;}
    public String getCountry() {return country;}

}
