package task_3;
import java.util.ArrayList;
import java.util.Scanner;

//V3.
//Array Intersection: Write a Java program that takes two arrays of integers and finds their intersection. Your program should prompt the user to enter the size and the elements of both arrays and then output the common elements between them.
//Make ArrayList from the result array and perform the following operations: a) Add an element to the end of the list; b) Remove an element from the list; c) Replace an element in the list; d) Sort the list in alphabetical order; e) Print the elements of the list;
//Make up the situation for ClassCastException. Catch it and display the explanation for your custom case.


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the first array: ");
        int size1 = scanner.nextInt();
        int[] array1 = new int[size1];
        System.out.println("Enter the elements of the first array: ");
        for (int i = 0; i < size1; i++) {
            array1[i] = scanner.nextInt();
        }

        System.out.print("Enter the size of the second array: ");
        int size2 = scanner.nextInt();
        int[] array2 = new int[size2];
        System.out.println("Enter the elements of the second array: ");
        for (int i = 0; i < size2; i++) {
            array2[i] = scanner.nextInt();
        }

        ArrayList<Integer> intersection = new ArrayList<>();
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                if (array1[i] == array2[j] && !intersection.contains(array1[i])) {
                    intersection.add(array1[i]);
                }
            }
        }

        System.out.println("Intersection of arrays: " + intersection);

        intersection.add(3);
        System.out.println("After adding 3: " + intersection);

        intersection.remove(Integer.valueOf(3));
        System.out.println("After removing 3: " + intersection);

        if (!intersection.isEmpty()) {
            intersection.set(0, 5);
            System.out.println("After replacing the first element with 5: " + intersection);
        }

        try {
            ArrayList<String> stringList = (ArrayList<String>) (ArrayList<?>) intersection;
            stringList.sort(null);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: Cannot sort integers as strings.");
        }

        System.out.println("List elements: " + intersection);

        scanner.close();
    }
}
