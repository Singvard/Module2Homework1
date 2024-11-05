package ru.sportmaster;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Переработанный консольный калькулятор, который умеет делить одно целое число на другое.
 *
 * @author SSaratov
 * @since 2024-11-03
 */
public class Task3 {

    /**
     * Метод забирает 2 целых числа (делимое, делитель) из консоли и
     * выполняет операцию деления. В случае неудачных действий в консоли
     * пользователю выводится уведомление об ошибке ввода, а в случае
     * деления на 0 - уведомление об ошибке вычисления. При этом выполнение
     * программы досрочно завершается.
     * В случае успеха в консоль выводится результат целочисленного деления.
     *
     * @param args стандартный параметр для main.
     */
    public static void main(String[] args) {
        try {
            var inputNumbers = inputInt();
            System.out.print(inputNumbers[0] / inputNumbers[1]);
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.print("Ошибка ввода: передать можно только целое число!");
        } catch (ArithmeticException ae) {
            System.out.print("Ошибка вычисления: нельзя делить на ноль!");
        }
    }

    /**
     * Метод выводит в консоль сообщения с предлежением ввести делимое и делитель,
     * а затем парсит указанные пользователь в целое число. В случае неудачи метод
     * передает на уровень выше соотвествующие исключение.
     *
     * @return массив из целых чисел. Первый элемент - делимое, второй - делитель.
     * @throws NoSuchElementException стандартная ошибка сканера
     * @throws IllegalStateException стандартная ошибка сканера
     */
    private static int[] inputInt() throws NoSuchElementException, IllegalStateException {
        var result = new int[2];
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите делимое (целое число): ");
            result[0] = scanner.nextInt();
            System.out.print("Введите делитель (целое число): ");
            result[1] = scanner.nextInt();
        }
        return result;
    }
}
