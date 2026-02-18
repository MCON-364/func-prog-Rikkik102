package edu.touro.las.mcon364.func_prog.exercises;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface Practice
 *
 * In this assignment you will:
 *  - Create and return different functional interfaces
 *  - Apply them
 *  - Practice chaining where appropriate
 *
 * IMPORTANT:
 *  - Use lambdas
 *  - Do NOT use anonymous classes
 */
public class FunctionalInterfaceExercises {

    // =========================================================
    // PART 1 — SUPPLIERS
    // =========================================================

    /**
     * 1) Create a Supplier that returns the current year.
     *
     * Hint:
     * You can get the current date using:
     *     LocalDate.now()
     *
     * Then extract the year using:
     *     getYear()
     *
     * Example (not the solution):
     *
     */
    public static Supplier<Integer> currentYearSupplier() {
      Supplier<Integer> currYear = () -> LocalDate.now().getYear();
        return currYear;
    }

    /**
     * 2) Create a Supplier that generates a random number
     * between 1 and 100.
     */
    public static Supplier<Integer> randomScoreSupplier() {
        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt(55, 87);
        return random;
    }

    // =========================================================
    // PART 2 — PREDICATES
    // =========================================================

    /**
     * 3) Create a Predicate that checks whether
     * a string is all uppercase.
     */
    public static Predicate<String> isAllUpperCase() {
        Predicate<String> isUpperCase = (str) -> str.matches("[A-Z, 0-9!@#$%^&*]+");
        return isUpperCase;
    }

    /**
     * 4) Create a Predicate that checks whether
     * a number is positive AND divisible by 5.
     *
     * Hint: consider chaining.
     */
    public static Predicate<Integer> positiveAndDivisibleByFive() {
        Predicate<Integer> hasConditions = x -> (x > 0) && (x%5 == 0);
        return hasConditions;
    }

    // =========================================================
    // PART 3 — FUNCTIONS
    // =========================================================

    /**
     * 5) Create a Function that converts
     * a temperature in Celsius to Fahrenheit.
     *
     * Formula: F = C * 9/5 + 32
     */
    public static Function<Double, Double> celsiusToFahrenheit() {
        Function<Double, Double> celciusToFarenheit = temp -> temp * 1.8 + 32;
        return celciusToFarenheit;
    }

    /**
     * 6) Create a Function that takes a String
     * and returns the number of vowels in it.
     *
     * Bonus: Make it case-insensitive.
     */
    public static Function<String, Integer> countVowels() {
        Function<String, Integer> vowelCount = word -> {
            int total = 0;
           for (char c: word.toUpperCase().toCharArray()) {
               if (c == 'A' || c== 'E' || c== 'I' || c== 'O' || c== 'U') {
                   total++;
               }
           }
           return total;
        };
        return vowelCount;
    }

    // =========================================================
    // PART 4 — CONSUMERS
    // =========================================================

    /**
     * 7) Create a Consumer that prints a value
     * surrounded by "***"
     *
     * Example output:
     * *** Hello ***
     */
    public static Consumer<String> starPrinter() {
        Consumer<String> printer = str -> System.out.println("*** " +str+ " ***");
        return printer;
    }

    /**
     * 8) Create a Consumer that prints the square
     * of an integer.
     */
    public static Consumer<Integer> printSquare() {
        Consumer<Integer> square = x -> System.out.println(x*x);
        return square;
    }

    // =========================================================
    // PART 5 — APPLYING FUNCTIONAL INTERFACES
    // =========================================================

    /**
     * 9) Apply:
     *  - A Predicate
     *  - A Function
     *  - A Consumer
     *
     * Process the list as follows:
     *  - Keep only strings longer than 3 characters
     *  - Convert them to lowercase
     *  - Print them
     */
    public static void processStrings(List<String> values) {
        Predicate<String> isLong = word -> word.length() > 3;
        Function<String, String> toLowerCase = word -> word.toLowerCase();
        Consumer<String> printer = System.out::println;
        for (String value : values) {
            if(isLong.test(value)) {
                printer.accept(toLowerCase.apply(value));
            }
        }
    }

    /**
     * 10) Apply:
     *  - A Supplier
     *  - A Predicate
     *  - A Consumer
     *
     * Generate 5 random scores.
     * Print only those above 70.
     */
    public static void generateAndFilterScores() {
       Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt(0, 101);
       Predicate<Integer> isOver70 = x -> x > 70;
       Consumer<Integer> printer = System.out::println;
       for (int i = 0; i < 5; i++) {
           int currNum = random.get();
           if (isOver70.test(currNum)) {
               printer.accept(currNum);
           }
       }
    }
}
