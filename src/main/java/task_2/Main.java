package task_2;

//V 3: Loop Statements
//Objective: To understand and use loop statements in Java with primitive types.
//        Instructions:
//Use a for loop to print the first 10 positive integers to the console.
//Use a while loop to print the first 10 positive integers to the console

public class Main {
    public static void main(String[] args) {
        System.out.println("For loop:");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        System.out.println("\nWhile loop:");
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
    }
}