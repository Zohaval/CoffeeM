package org.example;

import java.util.Scanner;

import static org.example.CoffeeRecipe.CAPPUCCINO;
import static org.example.CoffeeRecipe.ESPRESSO;

public class Menu {
    public static CoffeeMachine coffeeMachine = new CoffeeMachine();
    public static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("""
                \n1. Вкл/Вылк кофемашину
                2. Приготовить кофе
                3. Ингредиенты
                4. Логистика
                5. Выйти""");
    }

    public static void onOffCoffeeMachine() {
        if (coffeeMachine.getPowerOn()) {
            coffeeMachine.setPowerOn(false);
            System.out.println("Кофемашина выключена");
        }
        else {
            coffeeMachine.setPowerOn(true);
            System.out.println("Кофемашина включена");
        }
    }

    public static void isOn() {
        if (coffeeMachine.getPowerOn()) {
            menuCoffee();
        }
        else {
            System.out.println("Включите кофемашину, чтобы приготовить кофе");
        }
    }

    public static void menuCoffee() {
        System.out.println("""
                \n1. Приготовить 1 кофе
                2. Приготовить 3 кофе
                3. Приготовить n кофе
                4. Назад""");
        switch (scanner.nextLine()) {
            case "1":
                menuCupOfCoffee();
                break;
            case "2":
                menuThreeCupOfCoffee();
                break;
            case "3":
                menuNCupOfCoffee();
                break;
            case "4":
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;

        }
    }

    public static void menuCupOfCoffee() {
        System.out.println("""
                \n1. Приготовить Эспрессо
                2. Приготовить Капучино
                3. Назад""");
        switch (scanner.nextLine()) {
            case "1":
                makeCupOfCoffee(ESPRESSO);
                break;
            case "2":
                makeCupOfCoffee(CAPPUCCINO);
                break;
            case "3":
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;

        }
    }

    public static void menuThreeCupOfCoffee() {

    }

    public static void menuNCupOfCoffee() {

    }

    private static void makeCupOfCoffee(CoffeeRecipe recipe) {
        if (!CoffeeMachine.reviewException(recipe)) {
            return;
        }
        CoffeeMachine.makeCoffee(recipe);
        System.out.println(recipe.getName() + " готово");
    }

    public static void menuIngredients() {
        System.out.println("""
                \n1. Статус ингредиентов
                2. Добавить воды
                3. Добавить молока
                4. Добавить кофе
                5. Назад""");
        switch (scanner.nextLine()) {
            case "1":
                System.out.println("\nСтатус ингредиентов");
                CoffeeMachine.ingredientsStatus();
                break;
            case "2":
                System.out.print("Добавить воды: ");
                CoffeeMachine.addWater();
                break;
            case "3":
                System.out.print("Добавить молока: ");
                CoffeeMachine.addMilk();
                break;
            case "4":
                System.out.print("Добавить кофе: ");
                CoffeeMachine.addCoffee();
                break;
            case "5":
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuCleaningCoffeeMachine() {
        System.out.println("""
                \n1. Сколько кофе было сделано
                2. Очистить кофемашину
                3. Назад""");
        switch (scanner.nextLine()) {
            case "1":
                System.out.println("Сделано кружек кофе: " + CoffeeMachine.getCupOfCoffee());
                break;
            case "2":
                isClean();
                break;
            case "3":
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void isClean() {
        if (CoffeeMachine.getCupOfCoffee() == 0) {
            System.out.println("Кофемашина не требуется в чистке");
        }
        else {
            CoffeeMachine.setCupOfCoffee(0);
            System.out.println("Кофемашина очищена");
        }
    }
}
