import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
 * sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа,
 * над которыми должна быть произведена операция.
 *
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
 * если они одинаковые, и false в противном случае. Массивы могут быть любого типа данных,
 * но должны иметь одинаковую длину и содержать элементы одного типа.
 *
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 */

public class Main {
    /**
     * Task_01
     */
    private static class Calculator {
        private static <N extends Number> Number sum (N x, N y){
            return x.doubleValue() + y.doubleValue();
        }
        private static <N extends Number> Number subtract(N x, N y){
            return x.doubleValue() - y.doubleValue();
        }
        private static <N extends Number> Number multiply (N x, N y){
            return x.doubleValue() * y.doubleValue();
        }
        private static <N extends Number> Number divide(N x, N y){
            if (y.doubleValue() == 0){
                throw new ArithmeticException("You can't divide by zero");
            }
            return x.doubleValue() / y.doubleValue();
        }
    }
    /**
     * Task_02
     */
    private static <K, V> boolean compareArrays(K[] arr1, V[] arr2){
        if (arr1.length != arr2.length) return false;
        return (checkArrayClass(arr1) & checkArrayClass(arr2));
    }
    private static <T> boolean checkArrayClass(T[] checkArr){
        if(checkArr.length < 2) return true;
        for (int i = 1; i < checkArr.length; i++) {
            if(!checkArr[0].getClass().getSimpleName().equals(checkArr[i].getClass().getSimpleName())) return false;
        }
        return true;
    }

    /**
     * Task_03
     */
    private static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getFirst() {
            return key;
        }
        public V getSecond() {
            return value;
        }
        @Override
        public String toString() {
            return "First = " + key +
                    ", Second = " + value;
        }
    }

    public static void main(String[] args) {
        /**
         * Task_01
         */
        System.out.println(Calculator.sum(11, -1.0f));
        System.out.println(Calculator.subtract(12.5f, 7.5));
        System.out.println(Calculator.divide(16.5, 5.5));
        System.out.println(Calculator.divide(16.5, 5));
        System.out.println(Calculator.multiply(3, 5));
//        System.out.println(Calculator.divide(5, 0));
        System.out.println();
        /**
         * Task_02
         */
        Integer[] arr1 = new Integer[] {1, 2, 3, 4 ,5};
        Double[] arr2 = new Double[] {1.5, 2.3, 3.7, 4.9, 5.0};
        String[] arr3 = new String[] {"One", "Two", "Three"};
        String[] arr4 = new String[] {"First", "Second", "Third", "Fourth", "Fifth"};
        Number[] arr5 = new Number[] {1, 2, 3.5, 4, 5};
        System.out.println(compareArrays(arr1, arr2));
        System.out.println(compareArrays(arr2, arr3));
        System.out.println(compareArrays(arr1, arr4));
        System.out.println(compareArrays(arr1, arr5));
        System.out.println();
        /**
         * Task_03
         */
        Pair<Integer, String> pair = new Pair<>(1, "Second");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }


}
