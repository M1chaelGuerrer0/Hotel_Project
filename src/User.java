public class User {
    private String first;
    private String last;
    private String phoneNumber;
    private String email;
    private int userId;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private int cardId;
    private String password;


    public void setFirst(String first){this.first = first;}
    public void setLast(String last){this.last = last;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setEmail(String email){this.email = email;}
    public void setUserId(int userId){this.userId = userId;}

    public String getFirst(){return first;}
    public String getLast(){return last;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getEmail(){return email;}
    public int getUserId(){return userId;}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void displayUser() {
        System.out.println("userId: " + this.userId +"\nfirst name: " + this.first + "\nlast name: " + this.last +
                "\npassword: "+ this.password + "\nphoneNumber: " + this.phoneNumber + "\nemail: " + this.email +
                "\naddress1: " + this.address1 + "\naddress2: " + this.address2 + "\ncity: " + this.city +
                "\ncountry: " + this.country + "\nstate: " + this.state + "\nzipCode: " + this.zipCode +
                "\ncardId:" + this.cardId
        );
    }
}
