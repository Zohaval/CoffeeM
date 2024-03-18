package org.example;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import static org.example.CoffeeRecipe.CAPPUCCINO;
import static org.example.CoffeeRecipe.ESPRESSO;

public class Menu {
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
                        menuCreateProfile();
                        break;
                    case 4:
                        menuIngredients();
                        break;
                    case 5:
                        menuLogistics();
                        break;
                    case 6:
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
                \n1. Вкл/Выкл кофемашину
                2. Приготовить кофе
                3. Создать именной набор
                4. Ингредиенты
                5. Логистика
                6. Выйти""");
    }

    public static void menuCoffee() {
        System.out.println("""
                \n1. Приготовить 1 кофе
                2. Приготовить 3 кофе
                3. Приготовить n кофе
                4. Приготовить именной набор
                5. Назад""");
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
                menuProfile();
                break;
            case 5:
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
                CoffeeMachine.makeCupsOfCoffee(ESPRESSO, 1);
                break;
            case 2:
                CoffeeMachine.makeCupsOfCoffee(CAPPUCCINO, 1);
                break;
            case 3:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuCreateProfile() {
        Profile profile;

        System.out.println("Введите имя набора: ");
        String name = scanner.nextLine();
        name = scanner.nextLine();

        System.out.print("\nВведите количество порций эспрессо: ");
        int amountEspresso = scanner.nextInt();

        System.out.print("\nВведите количество порций капучино: ");
        int amountCappuccino = scanner.nextInt();

        profile = new Profile(name, amountEspresso, amountCappuccino);

        CoffeeMachine.saveProfile(profile);

    }

    public static void menuProfile() {
        LinkedList<Profile> listProfiles = CoffeeMachine.profiles;
        for (int i = 0; i < listProfiles.size(); i++) {
            Profile profile = listProfiles.get(i);
            System.out.println(profile.getNameProfile());
        }
    }

    public static void menuThreeCupOfCoffee() {
        System.out.println("""
                \n1. Приготовить 3 эспрессо
                2. Приготовить 3 капучино
                3. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                CoffeeMachine.makeCupsOfCoffee(ESPRESSO, 3);
                break;
            case 2:
                CoffeeMachine.makeCupsOfCoffee(CAPPUCCINO, 3);
                break;
            case 3:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
    }

    public static void menuNCupOfCoffee() {
        System.out.println("""
                \n1. Приготовить n порций эспрессо
                2. Приготовить n порций капучино
                3. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("\nКоличество порций эспрессо: ");
                CoffeeMachine.makeCupsOfCoffee(ESPRESSO, scanner.nextInt());
                break;
            case 2:
                System.out.print("\nКоличество порций капучино: ");
                CoffeeMachine.makeCupsOfCoffee(CAPPUCCINO, scanner.nextInt());
                break;
            case 3:
                break;
            default:
                System.out.println("\nНекорректный ввод");
                break;
        }
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
                CoffeeMachine.addWater(scanner.nextInt());
                break;
            case 3:
                System.out.print("Добавить молока: ");
                CoffeeMachine.addMilk(scanner.nextInt());
                break;
            case 4:
                System.out.print("Добавить кофе: ");
                CoffeeMachine.addCoffee(scanner.nextInt());
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
                4. История действий
                5. Назад""");
        switch (scanner.nextInt()) {
            case 1:
                CoffeeMachine.amountPreparedCoffee();
                break;
            case 2:
                CoffeeMachine.isClean();
                break;
            case 3:
                menuRecipesCoffee();
                break;
            case 4:
                CoffeeMachine.actions();
            case 5:
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