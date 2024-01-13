package com.simplegardening.model.Decoration;

import com.simplegardening.model.Price;

public abstract class Decorator extends Price {
    private final Price component;
    public Decorator(Price component){this.component = component;}
    @Override
    public float calculatePrice() {
        return this.component.calculatePrice();
    }

}
