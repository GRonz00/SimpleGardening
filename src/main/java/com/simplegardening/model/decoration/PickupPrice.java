package com.simplegardening.model.decoration;

import com.simplegardening.model.Price;

public class PickupPrice extends Decorator{
    private float pickupBase;
    private float priceKm;
    private double distance;
    public PickupPrice(Price component) {
        super(component);
    }
    public void defineThePrize(float pickupBase, float priceKm, double distance){
        this.pickupBase = pickupBase;
        this.priceKm = priceKm;
        this.distance = distance;
    }

    protected float applyPrize(float amount){
        return (float) (amount + this.pickupBase + this.distance * this.priceKm);
    }

    @Override
    public float calculatePrice() {
        float preliminaryResults = super.calculatePrice();
        preliminaryResults = this.applyPrize(preliminaryResults);
        return preliminaryResults;
    }

}
