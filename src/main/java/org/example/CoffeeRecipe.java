package org.example;

public enum CoffeeRecipe {
    ESPRESSO(100, 20, 50),
    CAPPUCCINO(150, 50, 50);
    private final int water;
    private final int milk;
    private final int coffee;

    CoffeeRecipe(int water, int milk, int coffee) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
    }

    public int getWater() {
        return water;
    }
    public int getMilk() {
        return milk;
    }
    public int getCoffee() {
        return coffee;
    }
}
