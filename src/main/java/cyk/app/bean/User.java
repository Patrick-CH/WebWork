package cyk.app.bean;

import java.sql.Date;

public class User {

    private String username;

    private String password;

    private String email;

    private Date birthday;

    private Integer balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                '}';
    }

    public User(){

    }

    public User(String username, String password, String email, Date birthday, Integer balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.balance = balance;
    }
}
