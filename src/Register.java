public class Register {
    private String first;
    private String last;
    private int phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipCode;
    private String country;


    public Register(String first,
                    String last,
                    int phoneNumber,
                    String email,
                    String address1,
                    String address2,
                    String city,
                    String state,
                    int zipCode,
                    String country){
        setFirst(first);
        setLast(last);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress1(address1);
        setAddress2(address2);
        setCity(city);
        setState(state);
        setZipCode(zipCode);
        setCountry(country);

    }


    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {return last;}


    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }



}


