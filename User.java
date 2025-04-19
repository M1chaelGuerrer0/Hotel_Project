public class User {
    /*
This class contains important information about the user.
@E.Sanchez
@version 1.0
 */

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
    private String password;
    /*
@param first  String - user first name
@param last String - user last name
@param phoneNumber Integer - phone number of user
@param email String  - email of user
@param userId Integer - user ID of user
@param address1 String  - address1 of user
@param address2 String  - address2 of user
@param city String  - city of user
@param state String  - state of user
@param zipCode Integer - zip code of user
@param country String  - country of user
@param password String  - password of user
@param cardId - card ID of user


*/


    // setters
    public void setFirst(String first){this.first = first;}
    public void setLast(String last){this.last = last;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setEmail(String email){this.email = email;}
    public void setUserId(int userId){this.userId = userId;}
    public void setPassword(String password) {this.password = password;}
    public void setAddress1(String address1) {this.address1 = address1;}
    public void setAddress2(String address2) {this.address2 = address2;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}
    public void setCountry(String country) {this.country = country;}

    // Getters
    public String getFirst(){return first;}
    public String getLast(){return last;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getEmail(){return email;}
    public int getUserId(){return userId;}
    public String getPassword() {return password;}
    public String getAddress1() {return address1;}
    public String getAddress2() {return address2;}
    public String getCity() {return city;}
    public String getZipCode() {return zipCode;}
    public String getState() {return state;}
    public String getCountry() {return country;}

    /*
     * Displays current User fields
     */
    public void displayUser() {
        System.out.println("userId: " + this.userId +"\nfirst name: " + this.first + "\nlast name: " + this.last +
                "\npassword: "+ this.password + "\nphoneNumber: " + this.phoneNumber + "\nemail: " + this.email +
                "\naddress1: " + this.address1 + "\naddress2: " + this.address2 + "\ncity: " + this.city +
                "\ncountry: " + this.country + "\nstate: " + this.state + "\nzipCode: " + this.zipCode
        );
    }
}
