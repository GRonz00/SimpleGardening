package com.simplegardening.bean.in;

public class ConvertAddressInBean {
    private String nation;
    private String city;
    private String street;
    private String pC;

    public ConvertAddressInBean(String nation, String city,String street, String pC){
        setNation(nation);
        setCity(city);
        setStreet(street);
        setpC(pC);
    }


    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getpC() {
        return pC;
    }

    public void setpC(String pC) {
        this.pC = pC;
    }
}
