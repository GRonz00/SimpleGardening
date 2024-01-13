package com.simplegardening.model.Decoration;

import com.simplegardening.model.Price;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ExtraHolidaysPrice extends Decorator{
    private LocalDate start;
    private LocalDate end;
    private float basePrice;
    private int percentageExtraHolidays;

    public ExtraHolidaysPrice(Price component) {
        super(component);
    }

    public void defineThePrize(LocalDate start,LocalDate end,float basePrice, int percentageExtraHolidays){
        this.basePrice=basePrice;
        this.percentageExtraHolidays=percentageExtraHolidays;
        this.start=start;
        this.end=end;
    }
    protected float applyPrize(float amount){

        List<LocalDate> allDates = start.datesUntil(end).collect(Collectors.toList());
        allDates.removeIf(localDate -> localDate.getDayOfWeek().getValue()!=6&&localDate.getDayOfWeek().getValue()!=7);
        int nHolidays = allDates.size();
        if(end.getDayOfWeek().getValue()==6||end.getDayOfWeek().getValue()==7){
            nHolidays=nHolidays+1;
        }
        return  amount + nHolidays * (basePrice /100*percentageExtraHolidays) ;
    }
    @Override
    public float calculatePrice() {
        float preliminaryResults = super.calculatePrice();
        preliminaryResults = this.applyPrize(preliminaryResults);
        return preliminaryResults;
    }

}
