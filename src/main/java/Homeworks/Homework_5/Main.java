package Homeworks.Homework_5;

import java.util.function.Predicate;
/**
 * Created by masaki on 5/9/2017.
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Mati", "Kati", "Aadu"};
        int[] nums = new int[] {40, 0, 4, -5, -14, 35, 1, 5, 6, 7, 8};

        print("Names that start with A", e -> e.startsWith("A"), names);
//        print("Values that are greater than ten", e -> e > 10, nums);
    }

    public static <T> void print(String message, Predicate<T> condition, T[] array){
        System.out.print(message + ": ");
        for (T el : array) {
            if (condition.test(el)){
                System.out.println(el);
            }
        }
    }

}
