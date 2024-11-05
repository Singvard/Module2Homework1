package ru.sportmaster;

import java.util.Arrays;

/**
 * Реализация структыры данных стек с предупреждением проблем,
 * связанных с выходом за пределы массива или обращения к пустым
 * ссылкам на хранимый объект.
 *
 * @author SSaratov
 * @since 2024-11-03
 */
public class Task4 {
    /**
     * Дополненная реализация класса Стек с лекции.
     * Представлен как статический внутренний класс.
     */
    public static class Stack {

        /**
         * Первоначальный размер стека
         */
        String[] storage = new String[10];

        /**
         * Индекс текущего свободного места для нового элемента в стеке
         */
        int count = 0; //

        /**
         * Метод добавления нового элемента типа String в стек.
         * Реализован так, что не позволяет добавить пустую ссылку
         * или пустую/состоящую из одних пробелов строку. Кроме того,
         * при заполнении стека увеличивает его размеры вдвое.
         *
         * @param newElement элемент, который добавляется в стек.
         */
        public void push(String newElement) {
            if (newElement == null || newElement.isBlank()) {
                System.out.println("Ошибка: нельзя добавить пустую запись в стек!");
            } else {
                storage[count] = newElement;
                count++;
                if (count > storage.length - 1) {
                    String[] temp = new String[storage.length * 2];
                    System.arraycopy(storage, 0, temp, 0, storage.length);
                    storage = temp;
                }
            }
        }

        /**
         * Метод извлечения последнего добавленного элемента из стека.
         * Освобождает занимаемое им место. Если стек пуст (счётчик равен 0),
         * то возвращает null.
         *
         * @return извлекаемый элемент типа String.
         */
        public String pop() {
            if (count > 0) {
                String out = storage[--count];
                storage[count] = null;
                return out;
            } else {
                System.out.println("Ошибка: стек пуст, возвращать нечего!");
                return null;
            }
        }

    }

    /**
     * Примитивные самописные автотесты.
     *
     * @param args стандартный параметр для main.
     */
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    /**
     * Тест №1 - добавление непустого элемента в стек.
     */
    private static void test1() {
        Stack stack = new Stack();
        stack.push("Элемент 1");
        String[] expected = new String[10];
        expected[0] = "Элемент 1";
        if (Arrays.equals(stack.storage, expected) && stack.count == 1) {
            System.out.println("Тест №1: успех");
        } else {
            System.out.println("Тест №1: неудача");
        }
    }

    /**
     * Тест №2 - вывод элемента из стека с одним элементом.
     */
    private static void test2() {
        Stack stack = new Stack();
        stack.storage[0] = "Элемент 1";
        stack.count = 1;
        String[] expected = new String[10];
        var outElement = stack.pop();
        if (Arrays.equals(stack.storage, expected) && outElement.equals("Элемент 1") && stack.count == 0) {
            System.out.println("Тест №2: успех");
        } else {
            System.out.println("Тест №2: неудача");
        }
    }

    /**
     * Тест №3 - вывод элемента из пустого стека.
     */
    private static void test3() {
        Stack stack = new Stack();
        String[] expected = new String[10];
        var outElement = stack.pop();
        if (Arrays.equals(stack.storage, expected) && outElement == null && stack.count == 0) {
            System.out.println("Тест №3: успех");
        } else {
            System.out.println("Тест №3: неудача");
        }
    }

    /**
     * Тест №4 - увеличение стека при переполнении первоначального объёма.
     */
    private static void test4() {
        Stack stack = new Stack();
        for (int i = 0; i < 9; i++) {
            stack.storage[i] = "Элемент 1";
        }
        stack.count = 9;
        String[] expected = new String[20];
        for (int i = 0; i < 9; i++) {
            expected[i] = "Элемент 1";
        }
        expected[9] = "Элемент 2";
        stack.push("Элемент 2");
        if (Arrays.equals(stack.storage, expected) && stack.count == 10) {
            System.out.println("Тест №4: успех");
        } else {
            System.out.println("Тест №4: неудача");
        }
    }

    /**
     * Тест №5 - добавление пустой записи в стек
     */
    private static void test5() {
        Stack stack = new Stack();
        String[] expected = new String[10];
        stack.push(null);
        stack.push("");
        stack.push("         ");
        if (Arrays.equals(stack.storage, expected) && stack.count == 0) {
            System.out.println("Тест №5: успех");
        } else {
            System.out.println("Тест №5: неудача");
        }
    }
}
