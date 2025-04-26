public class Card {
    private String holder_Name;
    private String card_Number;
    private String expiration;
    private String cvc;
    private String address1;
    private String address2 = "";
    private String city;
    private String country;
    private String state;
    private String zip_Code;
    private int card_id;
    private int guest_id;

    /////// Setters ///////
    public void setHolder_Name(String holder_Name) {this.holder_Name = holder_Name;}
    public void setCard_Number(String card_Number) {
        this.card_Number = card_Number;
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
    public void setZip_Code(String zip_Code) {this.zip_Code = zip_Code;}
    public void setCountry(String country) {this.country = country;}
    public void setCard_id(int card_id) {this.card_id = card_id;}
    public void setGuest_id(int guest_id) {this.guest_id = guest_id;}
    /////// Getters ///////
    public String getCard_Number() {
        return card_Number;
    }
    public String getExpiration() {
        return expiration;
    }
    public String getCvc() {
        return cvc;
    }
    public String getHolder_Name() {return holder_Name;}
    public String getAddress1() {return address1;}
    public String getAddress2() {return address2;}
    public String getCity() {return city;}
    public String getZip_Code() {return zip_Code;}
    public String getState() {return state;}
    public String getCountry() {return country;}
    public int getCard_id() {return card_id;}
    public int getGuest_id() {return guest_id;}

    public void displayCard() {
        System.out.println(
                "holder_Name: " + this.holder_Name +
                "\ncard_Number: " + this.card_Number +
                "\nexpiration: " + this.expiration +
                "\ncvc: " + this.cvc +
                "\naddress1: " + this.address1 +
                "\naddress2: " + this.address2 +
                "\ncity: " + this.city +
                "\ncountry: " + this.country +
                "\nstate: " + this.state +
                "\nzip_Code: " + this.zip_Code +
                "\ncard_id: " + this.card_id
        );
    }
}
