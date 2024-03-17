package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.CoffeeRecipe.CAPPUCCINO;
import static org.example.CoffeeRecipe.ESPRESSO;

public class Menu {
    public static CoffeeMachine coffeeMachine = new CoffeeMachine();
    public static Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            try {
                mainMenu();
                switch (scanner.nextInt()) {
                    case 1:
                        CoffeeMachine.onOffCoffeeMachine();
                        break;
                    case 2:
                        CoffeeMachine.isOn();
                        break;
                    case 3:
                        menuIngredients();
                        break;
                    case 4:
                        menuLogistics();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("\nНекорректный ввод");
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("\nНекорректный ввод");
                scanner.next();
            }
        }
    }

    public static void mainMenu() {
        System.out.println("""
                \n1. Вкл/Вылк кофемашину
                2. Приготовить кофе
                3. Ингредиенты
                4. Логистика
                5. Выйти""");
    }

    public static void menuCoffee() {
        System.out.println("""
                \n1. Приготовить 1 кофе
                2. Приготовить 3 кофе
                3. Приготовить n кофе
                4. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                menuCupOfCoffee();
                break;
            case 2:
                menuThreeCupOfCoffee();
                break;
            case 3:
                menuNCupOfCoffee();
                break;
            case 4:
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
        switch (scanner.nextInt()) {
            case 1:
                CoffeeMachine.makeCupOfCoffee(ESPRESSO);
                break;
            case 2:
                CoffeeMachine.makeCupOfCoffee(CAPPUCCINO);
                break;
            case 3:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuThreeCupOfCoffee() {
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Приготовить 3 эспрессо");
                CoffeeMachine.makeThreeCupOfCoffee(ESPRESSO);
                break;
            case 2:
                System.out.println("Приготовить 3 капучино");
                CoffeeMachine.makeThreeCupOfCoffee(CAPPUCCINO);
                break;
            case 3:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuNCupOfCoffee() {

    }

    public static void menuIngredients() {
        System.out.println("""
                \n1. Статус ингредиентов
                2. Добавить воды
                3. Добавить молока
                4. Добавить кофе
                5. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("\nСтатус ингредиентов");
                CoffeeMachine.ingredientsStatus();
                break;
            case 2:
                System.out.print("Добавить воды: ");
                int amountWater = scanner.nextInt();
                CoffeeMachine.addWater(amountWater);
                break;
            case 3:
                System.out.print("Добавить молока: ");
                int amountMilk = scanner.nextInt();
                CoffeeMachine.addMilk(amountMilk);
                break;
            case 4:
                System.out.print("Добавить кофе: ");
                int amountCoffee = scanner.nextInt();
                CoffeeMachine.addCoffee(amountCoffee);
                break;
            case 5:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuLogistics() {
        System.out.println("""
                \n1. Сколько порций кофе было сделано
                2. Очистить кофемашину
                3. Посмотреть рецепты
                4. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Сделано порций кофе: " + CoffeeMachine.getCupOfCoffee());
                break;
            case 2:
                CoffeeMachine.isClean();
                break;
            case 3:
                menuRecipesCoffee();
                break;
            case 4:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuRecipesCoffee() {
        System.out.println("""
                \n1. Рецепт эспрессо
                2. Рецепт капучино
                3. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                CoffeeMachine.recipeCoffee(ESPRESSO);
                break;
            case 2:
                CoffeeMachine.recipeCoffee(CAPPUCCINO);
                break;
            case 3:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }
}
