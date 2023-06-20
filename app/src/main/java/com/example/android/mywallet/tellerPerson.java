package com.example.android.mywallet;

public class tellerPerson {
    private String nameTeller;
    private int amuont;
    private String Cause;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public tellerPerson(String nameTeller, int amuont, String cause) {
        this.nameTeller = nameTeller;
        this.amuont = amuont;
        Cause = cause;

    }

    public tellerPerson(int id, String nameTeller, int amuont, String cause) {
        this.nameTeller = nameTeller;
        this.amuont = amuont;
        this.Cause = cause;
        this.id = id;
    }

    public String getNameTeller() {
        return nameTeller;
    }

    public void setNameTeller(String nameTeller) {
        this.nameTeller = nameTeller;
    }

    public int getAmuont() {
        return amuont;
    }

    public void setAmuont(int amuont) {
        this.amuont = amuont;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String cause) {
        Cause = cause;
    }
}
