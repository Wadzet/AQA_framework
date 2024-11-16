package task_2;

//V 3: Loop Statements
//Objective: To understand and use loop statements in Java with primitive types.
//        Instructions:
//Use a for loop to print the first 10 positive integers to the console.
//Use a while loop to print the first 10 positive integers to the console

public class Main {
    public static void main(String[] args) {
        printUsingForLoop(10);
        printUsingWhileLoop(10);
        printEvenNumbers(10);
    }

    public static void printUsingForLoop(int n) {
        System.out.println("For loop:");
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }

    public static void printUsingWhileLoop(int n) {
        System.out.println("\nWhile loop:");
        int i = 1;
        while (i <= n) {
            System.out.println(i);
            i++;
        }
    }

    public static void printEvenNumbers(int n) {
        System.out.println("\nEven Numbers:");
        for (int i = 2; i <= n; i += 2) {
            System.out.println(i);
        }
    }
}
