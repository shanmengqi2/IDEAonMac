package ATM_SYSTEM;

public class Account {
    private String userName;
    private String userID;
    private char gender;
    private String password;
    private double balance;
    private double limitation;


    public Account(String userName, char gender, String password, double balance, double limitation, String userID) {
        this.userName = userName;
        this.gender = gender;
        this.password = password;
        this.balance = balance;
        this.limitation = limitation;
        this.userID = userID;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimitation() {
        return limitation;
    }

    public void setLimitation(double limitation) {
        this.limitation = limitation;
    }
}
