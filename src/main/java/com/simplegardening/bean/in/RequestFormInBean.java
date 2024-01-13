package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.sql.Date;
import java.time.LocalDate;

public class RequestFormInBean {

    private Date start;
    private Date end;
    private float basePrice;
    private boolean pickupAvailable;
    private int maxKm;
    private float pickupBasePrice;
    private float kmPrice;
    private boolean newCustomer;
    private int extraHoliday;
    private  int amount;

    private PlantSize plantSize;
    private PlantType plantType;
    private int idSession;

    public RequestFormInBean(LocalDate start, LocalDate end, String basePrice, boolean pickupAvailable, String maxKm, String pickupBasePrice, String kmPrice) throws BeanException {
        setEnd(end,start);
        setBasePrice(basePrice);
        setKmPrice(kmPrice,pickupAvailable);
        setStart(start);
        setPickupAvailable(pickupAvailable);
        setMaxKm(maxKm,pickupAvailable);
        setPickupBasePrice(pickupBasePrice,pickupAvailable);
    }
    public void requestFormInBean2( boolean newCustomer, String extraHoliday, String plantSize, String plantType,String amount, int idSession) throws BeanException {
        setAmount(amount);
        setPlantSize(plantSize);
        setPlantType(plantType);
        setExtraHoliday(extraHoliday);
        setNewCustomer(newCustomer);
        setIdSession(idSession);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = Date.valueOf(start);

    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(LocalDate end,LocalDate start) throws BeanException {
        if(end.isBefore(start))throw new BeanException("Start","Is before of today");
        this.end = Date.valueOf(end);
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) throws BeanException {
        try {
            this.basePrice = Float.parseFloat(basePrice);
        }catch (NumberFormatException e ){
            throw new BeanException("base price",BeanException.ONLY_NUMBER_REASON);
        }
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

    public void setMaxKm(String maxKm, boolean pickupAvailable) throws BeanException {
        try {
            if(pickupAvailable) this.maxKm = Integer.parseInt(maxKm);
            else this.maxKm=0;
        }catch (NumberFormatException e){
            throw new BeanException("km max", BeanException.ONLY_NUMBER_REASON);
        }
    }

    public float getPickupBasePrice() {
        return pickupBasePrice;
    }

    public void setPickupBasePrice(String pickupBasePrice, boolean pickupAvailable) throws BeanException {
        try {
            if(pickupAvailable)this.pickupBasePrice = Float.parseFloat(pickupBasePrice);
            else this.pickupBasePrice=0;
        }catch (NumberFormatException e){
            throw new BeanException("pickup base",BeanException.ONLY_NUMBER_REASON);
        }
    }

    public float getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(String kmPrice, boolean pickupAvailable) throws BeanException {

        try {
            if(pickupAvailable)this.kmPrice = Float.parseFloat(kmPrice);
            else this.kmPrice=0;
        }catch (NumberFormatException e){
            throw new BeanException("km price",BeanException.ONLY_NUMBER_REASON);
        }
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

    public void setExtraHoliday(String extraHoliday) throws BeanException {

        try {
            this.extraHoliday = Integer.parseInt(extraHoliday);
        }catch (NumberFormatException e){
            throw new BeanException("Extra price",BeanException.ONLY_NUMBER_REASON);

        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(String amount) throws BeanException {
       try {
           this.amount = Integer.parseInt(amount);
       }catch (NumberFormatException e){
           throw new BeanException("amount",BeanException.ONLY_NUMBER_REASON);
       }
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public PlantSize getPlantSize() {
        return plantSize;
    }

    public void setPlantSize(String plantSize) throws BeanException {

        try{
            this.plantSize = PlantSize.valueOf(plantSize.toUpperCase());
        }catch (IllegalArgumentException e){
            throw  new BeanException("Size",e.getMessage());
        }
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) throws BeanException {

        try{
            this.plantType = PlantType.valueOf(plantType.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new BeanException("type",e.getMessage());
        }
    }
}
