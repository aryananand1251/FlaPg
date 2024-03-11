package com.example.myapplication;

public class User {

    private  String pname;
    private  String pregno;

    private String pownname;
    private  String State;
    private String City;
    private String District;
    private String Pincode;
    private  String Landmark;
    private  String  Locality;
     private String own_phone;

    public User() {
    }

    public User(String pname, String pregno, String pownname,String state, String city, String district, String pincode, String landmark, String locality, String own_phone) {
        this.pname = pname;
        this.pregno = pregno;
        this.pownname = pownname;
        State = state;
        City = city;
        District = district;
        Pincode = pincode;
        Landmark = landmark;
        Locality = locality;
        this.own_phone = own_phone;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPregno() {
        return pregno;
    }

    public void setPregno(String pregno) {
        this.pregno = pregno;
    }

    public String getPownname() {
        return pownname;
    }

    public void setPownname(String pownname) {
        this.pownname = pownname;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getOwn_phone() {
        return own_phone;
    }

    public void setOwn_phone(String own_phone) {
        this.own_phone = own_phone;
    }
}
