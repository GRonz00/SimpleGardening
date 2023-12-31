package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;

public class ConvertAddressInBean {
    private String nation;
    private String city;
    private String street;
    private String pC;

    public ConvertAddressInBean(String nation, String city,String street, String pC) throws BeanException {
        setNation(nation);
        setCity(city);
        setStreet(street);
        setpC(pC);
    }


    public String getNation() {
        return nation;
    }

    public void setNation(String nation) throws BeanException {
        if (nation.length() < 4) throw new BeanException("nation", BeanException.TOO_SHORT_REASON);
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws BeanException {
        if (city.length() < 4) throw new BeanException("city", BeanException.TOO_SHORT_REASON);
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws BeanException {
        if (street.length() < 4) throw new BeanException("username", BeanException.TOO_SHORT_REASON);
        this.street = street;
    }

    public String getpC() {
        return pC;
    }

    public void setpC(String pC) throws BeanException {
        if (pC.length() < 4) throw new BeanException("PC", BeanException.TOO_SHORT_REASON);
        if(pC.matches(String.valueOf(-9))) throw new BeanException("PC", BeanException.ONLY_NUMBER_REASON);
        this.pC = pC;
    }
}
