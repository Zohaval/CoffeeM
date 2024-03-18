package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.example.Exceptions.CleanException;
import org.example.Exceptions.NotEnoughCoffeeException;
import org.example.Exceptions.NotEnoughMilkException;
import org.example.Exceptions.NotEnoughWaterException;

public class CoffeeMachine {

    private static int water = 1000;
    private static int milk = 600;
    private static int coffee = 500;
    private static int cupOfCoffee = 0;
    private static int cupOfEspresso = 0;
    private static int cupOfCappuccino = 0;
    private static boolean powerOn = false;
    public static final int MAX_WATER = 1000;
    public static final int MAX_MILK = 600;
    public static final int MAX_COFFEE = 500;
    private static final int MAX_CUP_OF_COFFEE = 10;
    private static LinkedList<String> history = new LinkedList<>();
    public static LinkedList<Profile> profiles = new LinkedList<>();

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

    public static int getCupOfEspresso() {
        return cupOfEspresso;
    }

    public static int getCupOfCappuccino() {
        return cupOfCappuccino;
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

    public static void setCupOfEspresso(int cupOfEspresso) {
        CoffeeMachine.cupOfEspresso = cupOfEspresso;
    }

    public static void setCupOfCappuccino(int cupOfCappuccino) {
        CoffeeMachine.cupOfCappuccino = cupOfCappuccino;
    }

    public static void setPowerOn(boolean powerOn) {
        CoffeeMachine.powerOn = powerOn;
    }

    public static void onOffCoffeeMachine() {
        if (getPowerOn()) {
            setPowerOn(false);
            System.out.println("Кофемашина выключена");
            history.add("Кофемашина выключена");
        }
        else {
            setPowerOn(true);
            System.out.println("Кофемашина включена");
            history.add("Кофемашина включена");
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

    public static void saveProfile(Profile profile) {
        profiles.add(profile);
    }



    public static void ingredientsStatus() {
        System.out.printf("""
                Воды %d мл
                Молока %d мл
                Зёрен кофе %d г
                """,
                getWater(), getMilk(), getCoffee());
        history.add("Статус ингредиентов");
    }

    public static void addWater(int amountWater) {
        if (amountWater < 0) {
            System.out.println("\nНекорректный ввод");
        }
        else if (getWater() + amountWater <= MAX_WATER) {
            setWater(getWater() + amountWater);
            System.out.printf("Воды в кофемашине %d мл\n", getWater());
            history.add("Добавлено воды: " + amountWater + " мл");
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
            history.add("Добавлено молока: " + amountMilk + " мл");
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
            history.add("Добавлено кофе: " + amountCoffee + " г");
        }
        else {
            System.out.println("\nВы превысите объём резервуара");
        }
    }

    public static void makeCupsOfCoffee(CoffeeRecipe recipe, int amount) {
        for (int i = 0; i <= amount; i++) {
            if (reviewException(recipe, amount)) {
                return;
            }
        }
        makeCoffee(recipe, amount);
        System.out.println(recipe.getName() + " готово");
    }

    public static boolean reviewException(CoffeeRecipe recipe, int amount) {
        try {
            checkConditions(recipe, amount);
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

    public static void checkConditions(CoffeeRecipe recipe, int amount) throws CleanException, NotEnoughWaterException, NotEnoughMilkException, NotEnoughCoffeeException {
        checkClean();
        checkWater(recipe, amount);
        checkMilk(recipe, amount);
        checkCoffee(recipe, amount);
    }

    public static void checkClean() throws CleanException {
        if (getCupOfCoffee() >= MAX_CUP_OF_COFFEE) {
            throw new CleanException();
        }
    }

    public static void checkWater(CoffeeRecipe recipe, int amount) throws NotEnoughWaterException {
        if (recipe.getWater() * amount > getWater()) {
            throw new NotEnoughWaterException();
        }
    }

    public static void checkMilk(CoffeeRecipe recipe, int amount) throws NotEnoughMilkException {
        if (recipe.getMilk() * amount >= getMilk()) {
            throw new NotEnoughMilkException();
        }
    }

    public static void checkCoffee(CoffeeRecipe recipe, int amount) throws NotEnoughCoffeeException {
        if (recipe.getCoffee() * amount >= getCoffee()) {
            throw new NotEnoughCoffeeException();
        }
    }

    public static void makeCoffee(CoffeeRecipe recipe, int amount) {
        setWater(getWater() - recipe.getWater() * amount);
        setMilk(getMilk() - recipe.getMilk() * amount);
        setCoffee(getCoffee() - recipe.getCoffee() * amount);
        setCupOfCoffee(getCupOfCoffee() + amount);
        if (recipe.getName().equals("Эспрессо")) {
            setCupOfEspresso(getCupOfEspresso() + amount);
        }
        if (recipe.getName().equals("Капучино")) {
            setCupOfCappuccino(getCupOfCappuccino() + amount);
        }
    }

    public static void amountPreparedCoffee() {
        System.out.printf("""
                \nСделано порций эспрессо: %d
                Сделано порций капучино: %d
                """, CoffeeMachine.getCupOfEspresso(), CoffeeMachine.getCupOfCappuccino());
        history.add("Сколько порций кофе было сделано");
    }

    public static void isClean() {
        if (getCupOfCoffee() == 0) {
            System.out.println("Кофемашина не требуется в чистке");
        }
        else {
            setCupOfCoffee(0);
            setCupOfEspresso(0);
            setCupOfCappuccino(0);
            System.out.println("Кофемашина очищена");
            history.add("Очистить кофемашину");
        }
    }

    public static void recipeCoffee(CoffeeRecipe coffeeRecipe) {
        System.out.printf("Рецепт для %s:\nВоды %d мл\nМолока %d мл\nЗёрен кофе %d г\n",
                coffeeRecipe.getName(), coffeeRecipe.getWater(), coffeeRecipe.getMilk(), coffeeRecipe.getCoffee());
        history.add("Рецепт " + coffeeRecipe.getName());
    }

    public static void actions() {
        history.add("История действий");
        for (String action : history) {
            System.out.println(action);
        }
        System.out.printf("""
                Приготовленно порций эспрессо: %d
                Приготовленно порций капучино: %d
                """, getCupOfEspresso(), getCupOfCappuccino());
    }
}
