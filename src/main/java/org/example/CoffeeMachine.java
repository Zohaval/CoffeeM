package org.example;

import java.util.Scanner;

import static org.example.CoffeeRecipe.ESPRESSO;

public class CoffeeMachine {

    public static Scanner scanner = new Scanner(System.in);

    private static int water = 700;
    private static int milk = 300;
    private static int coffee = 450;
    private static int cupOfCoffee = 2;
    private boolean powerOn = false;
    public static final int MAX_WATER = 1000;
    public static final int MAX_MILK = 600;
    public static final int MAX_COFFEE = 500;
    private static final int MAX_CUP_OF_COFFEE = 10;

    public static int getWater() {
        return water;
    }

    public static int getMilk() {
        return milk;
    }

    public static int getCoffee() {
        return coffee;
    }

    public static int getCupOfCoffee() {
        return cupOfCoffee;
    }

    public boolean getPowerOn() {
        return powerOn;
    }

    public static void setWater(int water) {
        CoffeeMachine.water = water;
    }

    public static void setMilk(int milk) {
        CoffeeMachine.milk = milk;
    }

    public static void setCoffee(int coffee) {
        CoffeeMachine.coffee = coffee;
    }

    public static void setCupOfCoffee(int cupOfCoffee) {
        CoffeeMachine.cupOfCoffee = cupOfCoffee;
    }

    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }

    public static void ingredientsStatus() {
        System.out.println("Воды " + getWater() + " мл");
        System.out.println("Молока " + getMilk() + " мл");
        System.out.println("Зёрен кофе " + getCoffee() + " г");
        System.out.println();
    }

    public static void addWater() {
        int ingredient = scanner.nextInt();
        if (getWater() + ingredient <= MAX_WATER) {
            setWater(getWater() + ingredient);
            System.out.println("Воды в кофемашине " + getWater() + " мл");
        }
        else {
            System.out.println("Вы превысите объём резервуара");
        }
    }

    public static void addMilk() {
        int ingredient = scanner.nextInt();
        if (getMilk() + ingredient <= MAX_MILK) {
            setMilk(getMilk() + ingredient);
            System.out.println("Молока в кофемашине " + getMilk() + " мл");
        }
        else {
            System.out.println("Вы превысите объём резервуара");
        }
    }

    public static void addCoffee() {
        int ingredient = scanner.nextInt();
        if (getCoffee() + ingredient <= MAX_COFFEE) {
            setCoffee(getCoffee() + ingredient);
            System.out.println("Кофе в кофемашине " + getCoffee() + " г");
        }
        else {
            System.out.println("Вы превысите объём резервуара");
        }
    }

    public static boolean reviewException(CoffeeRecipe recipe) {
        try {
            checkConditions(recipe);
        }
        catch (CleanException ex) {
            System.out.println("Кофемашина грязная");
            return false;
        }
        catch (NotEnoughWaterException ex) {
            System.out.println("Недостаточно воды");
            return false;
        }
        catch (NotEnoughMilkException ex) {
            System.out.println("Недостаточно молока");
            return false;
        }
        catch (NotEnoughCoffeeException ex) {
            System.out.println("Недостаточно кофе");
            return false;
        }
        return true;
    }

    public static void checkConditions(CoffeeRecipe recipe) throws CleanException, NotEnoughWaterException, NotEnoughMilkException, NotEnoughCoffeeException {
        CoffeeMachine.checkClean();
        CoffeeMachine.checkWater(recipe);
        CoffeeMachine.checkMilk(recipe);
        CoffeeMachine.checkCoffee(recipe);
        CoffeeMachine.setCupOfCoffee(CoffeeMachine.getCupOfCoffee() + 1);
    }

    public static void checkClean() throws CleanException {
        if (getCupOfCoffee() >= MAX_CUP_OF_COFFEE) {
            throw new CleanException();
        }
    }

    public static void checkWater(CoffeeRecipe recipe) throws NotEnoughWaterException {
        if (recipe.getWater() > getWater()) {
            throw new NotEnoughWaterException();
        }
    }

    public static void checkMilk(CoffeeRecipe recipe) throws NotEnoughMilkException {
        if (recipe.getMilk() >= getMilk()) {
            throw new NotEnoughMilkException();
        }
    }

    public static void checkCoffee(CoffeeRecipe recipe) throws NotEnoughCoffeeException {
        if (recipe.getCoffee() >= getCoffee()) {
            throw new NotEnoughCoffeeException();
        }
    }

    public static void makeCoffee(CoffeeRecipe recipe) {
        CoffeeMachine.setWater(CoffeeMachine.getWater() - recipe.getWater());
        CoffeeMachine.setMilk(CoffeeMachine.getMilk() - recipe.getMilk());
        CoffeeMachine.setCoffee(CoffeeMachine.getCoffee() - recipe.getCoffee());
    }

    public static void main(String[] args) {
        while (true) {
            Menu.mainMenu();
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    Menu.onOffCoffeeMachine();
                    break;
                case 2:
                    Menu.isOn();
                    break;
                case 3:
                    Menu.menuIngredients();
                    break;
                case 4:
                    Menu.menuCleaningCoffeeMachine();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nНекорректный ввод");
                    break;
            }
        }
    }
}
