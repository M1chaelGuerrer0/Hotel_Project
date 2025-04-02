public class login {
    private int userId;
    private int cardId;
    private String password;
    private String accountType;
    private String email;

    public void  setUserId(int userId){this.userId = userId;}
    public void setcardId(int cardId){this.cardId = cardId;}
    public void setPassword(String password){this.password = password;}
    public void setAccountType(String accountType){this.accountType = accountType;}
    public void setEmail(String email){this.email= email;};

    public int getUserId(){return userId;}
    public int getCardId(){return cardId;}
    public String getPassword(){return password;}
    public String getAccountType(){return accountType;}
    public String getEmail(){return email;}







}
