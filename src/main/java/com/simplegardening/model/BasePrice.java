package com.simplegardening.model;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class BasePrice extends Price {
    private float amount;
    public BasePrice(LocalDate start, LocalDate end, float dayPrice){
        this.setAmount(start,end,dayPrice);
    }

    public void setAmount(LocalDate start, LocalDate end, float dayPrice) {
        this.amount = (DAYS.between(start,end)+1)*dayPrice;
    }

    @Override
    public float calculatePrice() {
        return this.amount;
    }
}
