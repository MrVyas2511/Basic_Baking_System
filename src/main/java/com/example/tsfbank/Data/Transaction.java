package com.example.tsfbank.Data;

public class Transaction {

    private String fromUser,toUser,Amount,Date;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private  Integer status;
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Transaction(String fromUser, String toUser, String amount, String date,Integer status) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        Amount = amount;
        Date = date;
        this.status=status;
    }
}
