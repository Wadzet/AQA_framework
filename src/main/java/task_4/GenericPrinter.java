package task_4;

import java.util.Arrays;

public class GenericPrinter {

    public static <T> void printArray(T[] array) {
        Arrays.stream(array).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4};
        printArray(intArray);

        String[] strArray = {"Hello", "Vadym", "Java"};
        printArray(strArray);
    }
}
