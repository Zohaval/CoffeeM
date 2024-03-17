package org.example;

import org.example.Exceptions.CleanException;
import org.example.Exceptions.NotEnoughCoffeeException;
import org.example.Exceptions.NotEnoughMilkException;
import org.example.Exceptions.NotEnoughWaterException;

public class CoffeeMachine {

    private static int water = 700;
    private static int milk = 300;
    private static int coffee = 450;
    private static int cupOfCoffee = 2;
    private static boolean powerOn = false;
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

    public static boolean getPowerOn() {
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

    public static void setPowerOn(boolean powerOn) {
        CoffeeMachine.powerOn = powerOn;
    }

    public static void onOffCoffeeMachine() {
        if (getPowerOn()) {
            setPowerOn(false);
            System.out.println("Кофемашина выключена");
        }
        else {
            setPowerOn(true);
            System.out.println("Кофемашина включена");
        }
    }

    public static void isOn() {
        if (getPowerOn()) {
            Menu.menuCoffee();
        }
        else {
            System.out.println("Включите кофемашину, чтобы приготовить кофе");
        }
    }

    public static void ingredientsStatus() {
        System.out.printf("""
                Воды %d мл
                Молока %d мл
                Зёрен кофе %d г
                """,
                getWater(), getMilk(), getCoffee());
    }

    public static void addWater(int amountWater) {
        if (amountWater < 0) {
            System.out.println("\nНекорректный ввод");
        }
        else if (getWater() + amountWater <= MAX_WATER) {
            setWater(getWater() + amountWater);
            System.out.printf("Воды в кофемашине %d мл\n", getWater());
        }
        else {
            System.out.println("\nВы превысите объём резервуара");
        }
    }

    public static void addMilk(int amountMilk) {
        if (amountMilk < 0) {
            System.out.println("\nНекорректный ввод");
        }
        else if (getMilk() + amountMilk <= MAX_MILK) {
            setMilk(getMilk() + amountMilk);
            System.out.printf("Молока в кофемашине %d мл\n", getMilk());
        }
        else {
            System.out.println("\nВы превысите объём резервуара");
        }
    }

    public static void addCoffee(int amountCoffee) {
        if (amountCoffee < 0) {
            System.out.println("\nНекорректный ввод");
        }
        else if (getCoffee() + amountCoffee <= MAX_COFFEE) {
            setCoffee(getCoffee() + amountCoffee);
            System.out.printf("Кофе в кофемашине %d г\n", getCoffee());
        }
        else {
            System.out.println("\nВы превысите объём резервуара");
        }
    }

    public static void makeCupOfCoffee(CoffeeRecipe recipe) {
        if (CoffeeMachine.reviewException(recipe)) {
            return;
        }
        CoffeeMachine.makeCoffee(recipe);
        System.out.println(recipe.getName() + " готово");
    }

    public static void makeThreeCupOfCoffee(CoffeeRecipe recipe) {
        for (int i = 0; i <= 3; i++) {
            if (CoffeeMachine.reviewException(recipe)) {
                return;
            }
        }
        CoffeeMachine.makeCoffee(recipe);
        System.out.println(recipe.getName() + " готово");
    }

    public static boolean reviewException(CoffeeRecipe recipe) {
        try {
            checkConditions(recipe);
        }
        catch (CleanException ex) {
            System.out.println("Кофемашина грязная");
            return true;
        }
        catch (NotEnoughWaterException ex) {
            System.out.println("Недостаточно воды");
            return true;
        }
        catch (NotEnoughMilkException ex) {
            System.out.println("Недостаточно молока");
            return true;
        }
        catch (NotEnoughCoffeeException ex) {
            System.out.println("Недостаточно кофе");
            return true;
        }
        return false;
    }

    public static void checkConditions(CoffeeRecipe recipe) throws CleanException, NotEnoughWaterException, NotEnoughMilkException, NotEnoughCoffeeException {
        checkClean();
        checkWater(recipe);
        checkMilk(recipe);
        checkCoffee(recipe);
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
        setWater(getWater() - recipe.getWater());
        setMilk(getMilk() - recipe.getMilk());
        setCoffee(getCoffee() - recipe.getCoffee());
        setCupOfCoffee(getCupOfCoffee() + 1);
    }

    public static void isClean() {
        if (getCupOfCoffee() == 0) {
            System.out.println("Кофемашина не требуется в чистке");
        }
        else {
            setCupOfCoffee(0);
            System.out.println("Кофемашина очищена");
        }
    }

    public static void recipeCoffee(CoffeeRecipe coffeeRecipe) {
        System.out.printf("Рецепт для %s:\nВоды %d мл\nМолока %d мл\nЗёрен кофе %d г\n",
                coffeeRecipe.getName(), coffeeRecipe.getWater(), coffeeRecipe.getMilk(), coffeeRecipe.getCoffee());
    }
}
//      CoffeeRecipe[] coffeeRecipe = CoffeeRecipe.values();
