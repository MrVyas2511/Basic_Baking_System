package com.example.tsfbank.Data;

public class UserData {
    public  String Name;
    public String Acc_No ;
    public  String  ifsc;
    public  String email;
    public  String city;
    public  String Balance;
    public String M_no;

    public UserData(String name, String acc_No, String ifsc, String email, String city, String balance, String m_no) {
        this.Name = name;
        this.Acc_No = acc_No;
        this.ifsc = ifsc;
        this.email = email;
        this.city = city;
        this.Balance = balance;
        this.M_no = m_no;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String  getAcc_No() {
        return Acc_No;
    }

    public void setAcc_No(String acc_No) {
        Acc_No = acc_No;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String  getBalance() {
        return Balance;
    }

    public void setBalance(String  balance) {
        Balance = balance;
    }

    public String getM_no() {
        return M_no;
    }

    public void setM_no(String m_no) {
        M_no = m_no;
    }




}
