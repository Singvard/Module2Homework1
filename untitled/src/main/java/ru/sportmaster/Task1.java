package ru.sportmaster;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Простейший консольный калькулятор, который умеет делить одно целое число на другое.
 *
 * @author SSaratov
 * @since 2024-11-03
 */

public class Task1 {
    /**
     * Текст первого сообщения, отображаемого пользователю в консоли.
     */
    private static final String FIRST_MESSAGE = """
            Привет!
            Я - калькулятор простой, умею только делить 2 целых числа. Результат округляется до тысячных.
            Введите делимое:\s""";

    /**
     * Текст второго сообщения, отображаемого пользователю в консоли.
     */
    private static final String SECOND_MESSAGE = "Введите делитель: ";

    /**
     * Текст сообщения об ошибке, если Scanner.parseInt(), не может корректно
     * обработать строку, введённую пользователем.
     */
    private static final String INPUT_ERROR_MESSAGE = """
            Необходимо ввести целое число от -2 147 483 648 до 2 147 483 647.
            Повторите попытку:\s""";

    /**
     * Текст сообщения об ошибке, если делитель равен 0.
     */
    private static final String INFINITY_ERROR_MESSAGE = "Эй, на ноль делить нельзя!";
    private static final String RESULT_MESSAGE = """
            Ваш результат:
            %d ÷ %d = %s
            """;

    /**
     * Статическое поле класса, показывает была ли ошибка деления на ноль,
     * по умолчанию равно true (т.е. ошибки не было).
     */
    private static boolean notError = true;

    public static void main(String[] args) {

        var dividend = inputNumber(Numbers.ONE.valueOf());
        var divisor = inputNumber(Numbers.TWO.valueOf());
        printResult(getQuotient(dividend, divisor), dividend, divisor);
    }

    /**
     * Метод выводит в консоль информацию для пользователя, предлагает
     * ввести делимое и делитель. Также создаёт объект Scanner и
     * передает управление им в getNumber.
     *
     * @param step этап. Всего их 2. На первом этапе пользователь вводит
     *             делимое, на втором - делитель.
     * @return возвращает целое число (делимое или делитель).
     * @see Numbers предоставляет значения для входного параметра.
     */
    private static int inputNumber(int step) {
        Scanner sc = new Scanner(System.in);

        switch (step) {
            case 1 -> System.out.print(FIRST_MESSAGE);
            case 2 -> System.out.print(SECOND_MESSAGE);
        }
        return getNumber(sc, step);
    }

    /**
     * Метод забирает из консоли строку, введённую пользователем и
     * пытается распарсить её как целое число. В случае возникновении
     * ошибки пользователю выводится уведомление {@value INPUT_ERROR_MESSAGE}
     * и предлагается повторить ввод.
     *
     * @param scanner объект типа Scanner, обрабатывает значения из консоли.
     * @param step    этап. Всего их 2. На первом вводится делимое, на
     *                втором делитель. В конце второго этапа scanner закрывается.
     * @return возвращает целое число (делимое или делитель).
     */
    private static int getNumber(Scanner scanner, int step) {
        boolean flag = true;
        var number = 0;
        while (flag) {
            try {
                number = scanner.nextInt();
                flag = false;
            } catch (RuntimeException e) {
                System.out.print(INPUT_ERROR_MESSAGE);
                scanner.nextLine();
            }
        }

        if (step == 2) {
            scanner.close();
        }

        return number;
    }

    /**
     * Метод сперва пытается выполнить деление в целых числах,
     * чтобы поймать ArithmeticException при делении на 0. Перехваченное
     * исключение изменяет флаг isError на false и выводит в консоль
     * сообщение об ошибке {@value INFINITY_ERROR_MESSAGE}. Далее делимое и
     * делитель кастуются к типу float и выполняется деление.
     *
     * @param dividend делимое (целое число).
     * @param divisor  делитель (целое число).
     * @return частное от деления в формате действительного числа.
     */
    private static float getQuotient(int dividend, int divisor) {
        try {
            var quotient = dividend / divisor;
        } catch (ArithmeticException ae) {
            notError = false;
            System.out.print(INFINITY_ERROR_MESSAGE);
        }
        return (float) dividend / (float) divisor;
    }

    /**
     * Метод выводит в консоль результат деления в формате:
     * делимое ÷ делитель = частное, если делитель не равен 0.
     * Частное будет действительным числом (десятичная дробь) с
     * округлением до 3-го порядка после запятой. "Лишние" нули не выводятся.
     *
     * @param result   частное.
     * @param dividend делимое.
     * @param divisor  делитель.
     */
    private static void printResult(float result, int dividend, int divisor) {
        if (notError) {
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.printf(RESULT_MESSAGE, dividend, divisor, df.format(result));
        }
    }
}

/**
 * Перечисление, которое хранит значения 1 и 2
 */
enum Numbers {
    ONE, TWO;

    /**
     * метод возвращает числовое значение констант (целое число)
     *
     * @return 1 для ONE, 2 для TWO
     */
    public int valueOf() {
        return this.ordinal() + 1;
    }
}