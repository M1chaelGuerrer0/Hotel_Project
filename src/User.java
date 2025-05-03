public class User {
    private String first_Name;
    private String last_Name;
    private String phone_Number;
    private String email;
    private String address1;
    private String address2 = "";
    private String city;
    private String state;
    private String zip_Code;
    private String country;
    private String password;
    private int user_id = 0;

    public User(){}
    public User(String first_Name, String last_Name, String email, String password, String phone_Number, String address1,
                String address2, String zip_Code, String city, String state, String country,  int user_id){
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.password = password;
        this.phone_Number = phone_Number;
        this.address1 = address1;
        this.address2 = address2;
        this.zip_Code = zip_Code;
        this.city = city;
        this.state = state;
        this.country = country;
        this.user_id = user_id;
    }
    // setters
    public void setFirst_Name(String first_Name){this.first_Name = first_Name;}
    public void setLast_Name(String last_Name){this.last_Name = last_Name;}
    public void setPhone_Number(String phone_Number){this.phone_Number = phone_Number;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setAddress1(String address1) {this.address1 = address1;}
    public void setAddress2(String address2) {this.address2 = address2;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZip_Code(String zip_Code) {this.zip_Code = zip_Code;}
    public void setCountry(String country) {this.country = country;}
    public void setUser_id(int user_id) {this.user_id = user_id;}
    // Getters
    public String getFirst_Name(){return first_Name;}
    public String getLast_Name(){return last_Name;}
    public String getPhone_Number(){return phone_Number;}
    public String getEmail(){return email;}
    public String getPassword() {return password;}
    public String getAddress1() {return address1;}
    public String getAddress2() {return address2;}
    public String getCity() {return city;}
    public String getZip_Code() {return zip_Code;}
    public String getState() {return state;}
    public String getCountry() {return country;}
    public int getUser_id() {return user_id;}
    /*
     * Displays current User fields
     */
    @Override
    public String toString() {
        return "\nfirst_name: " + this.first_Name +
                "\nlast_name: " + this.last_Name +
                "\nemail: " + this.email +
                "\npassword: "+ this.password +
                "\nphone_Number: " + this.phone_Number +
                "\naddress1: " + this.address1 +
                "\naddress2: " + this.address2 +
                "\nzip_Code: " + this.zip_Code +
                "\ncity: " + this.city +
                "\nstate: " + this.state +
                "\ncountry: " + this.country +
                "\nuser_id: " + this.user_id + "\n";
    }
}
