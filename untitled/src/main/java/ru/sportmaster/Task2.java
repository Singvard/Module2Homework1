package ru.sportmaster;

import java.util.Scanner;

/**
 * Приложение хранит пин-коды от банковских карт и выводит их по запросу
 * пользователя из консоли.
 *
 * @author SSaratov
 * @since 2024-11-03
 */
public class Task2 {
    /**
     * Строковый массив, содержащий пин-коды от 4-х банковских карт.
     */
    private static final String[] PIN_CODES = {"5049", "4472", "0298", "8953"};

    /**
     * Количество пин-кодов в наборе.
     */
    private static final int LIMIT = PIN_CODES.length;

    /**
     * Текст первоначального сообщения, отображаемого пользователю в консоли.
     */
    private static final String GREETING_MESSAGE = """
            Итак, Григорий, опять забыл пин-код от карты?
            Ладно, не переживай, сейчас всё вспомним.
            Просто укажи порядковый номер нужной карты:\s""";

    /**
     * Текст сообщения об ошибке, отображаемого пользователю в консоли,
     * если он ввёл некорректный порядковый номер карты.
     */
    private static final String ERROR_MESSAGE = "Григорий, укажи целое число от 1 до %d: ";

    /**
     * Текст сообщения, содержащий пин-код указанной пользователем карты.
     */
    private static final String FINAL_MESSAGE = "Пин-код: %s";

    public static void main(String[] args) {
        System.out.print(GREETING_MESSAGE);
        printPinCode(getPinCode(getCardNumber()));
    }

    /**
     * Метод забирает из консоли порядковый номер карты. Если пользователь
     * ввёл значение, которые невозможно распарсить в целое число, либо
     * значение меньше 1 или больше, чем число хранимых пин-кодов, то это
     * возбудит исключение, что приведёт к выводу сообщения об ошибке {@value ERROR_MESSAGE}
     * с предложением повторно ввести порядковый номер карты.
     * @return порядковый номер банковской карты пользователя.
     */
    private static int getCardNumber() {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        int cardNumber = 0;
        while (flag) {
            try {
                cardNumber = scanner.nextInt();
                if (cardNumber < 1 || cardNumber > LIMIT) {
                    throw new IllegalArgumentException();
                }
                flag = false;
            } catch (RuntimeException e) {
                System.out.printf(ERROR_MESSAGE, LIMIT);
                scanner.nextLine();
            }
        }
        scanner.close();
        return cardNumber;
    }

    /**
     * Метод приводит порядковый номер карты к порядковому номеру элемента массива.
     * @param cardNumber порядковый номер банковской карты пользователя.
     * @return порядковый номер элемента массива, содержащего пин-код.
     */
    private static String getPinCode(int cardNumber) {
        return PIN_CODES[cardNumber - 1];
    }

    /**
     * Метод выводит в консоль пин-код для заданной пользователем карты.
     * @param pinCode пин-код банковской карты.
     */
    private static void printPinCode(String pinCode) {
        System.out.printf(FINAL_MESSAGE, pinCode);
    }
}