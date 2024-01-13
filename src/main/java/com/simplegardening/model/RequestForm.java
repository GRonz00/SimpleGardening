package com.simplegardening.model;

import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.sql.Date;

public class RequestForm {
    private Date start;
    private Date end;
    private float basePrice;
    private boolean pickupAvailable;
    private int maxKm;
    private float pickupBasePrice;
    private float kmPrice;
    private boolean newCustomer;
    private int extraHoliday;
    private int amount;
    private PlantType plantType;
    private PlantSize plantSize;
    private String pro;
    private int idRequestForm;


    public RequestForm( Date start, Date end, float basePrice, boolean pickupAvailable, int maxKm, float pickupBasePrice, float kmPrice) {
        setEnd(end);
        setBasePrice(basePrice);
        setKmPrice(kmPrice);
        setStart(start);
        setPickupAvailable(pickupAvailable);
        setMaxKm(maxKm);
        setPickupBasePrice(pickupBasePrice);
    }
    public void requestForm2(PlantSize plantSize, PlantType plantType, boolean newCustomer, int extraHoliday, int amount){
        setNewCustomer(newCustomer);
        setAmount(amount);
        setPlantSize(plantSize);
        setPlantType(plantType);
        setExtraHoliday(extraHoliday);
        setAmount(amount);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isPickupAvailable() {
        return pickupAvailable;
    }

    public void setPickupAvailable(boolean pickupAvailable) {
        this.pickupAvailable = pickupAvailable;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public float getPickupBasePrice() {
        return pickupBasePrice;
    }

    public void setPickupBasePrice(float pickupBasePrice) {
        this.pickupBasePrice = pickupBasePrice;
    }

    public float getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(float kmPrice) {
        this.kmPrice = kmPrice;
    }

    public boolean isNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(boolean newCustomer) {
        this.newCustomer = newCustomer;
    }

    public int getExtraHoliday() {
        return extraHoliday;
    }

    public void setExtraHoliday(int extraHoliday) {
        this.extraHoliday = extraHoliday;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public PlantSize getPlantSize() {
        return plantSize;
    }

    public void setPlantSize(PlantSize plantSize) {
        this.plantSize = plantSize;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public int getIdRequestForm() {
        return idRequestForm;
    }

    public void setIdRequestForm(int idRequestForm) {
        this.idRequestForm = idRequestForm;
    }
}
