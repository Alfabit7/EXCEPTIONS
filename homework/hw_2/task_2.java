/*
 * Если необходимо, исправьте данный код (задание 2
 * https://docs.google.com/document/d/
 * 17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)
 * 
        try {
        int d = 0;
        double catchedRes1 = intArray[8] / d;
        System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
        System.out.println("Catching exception: " + e);
        }
*/

package hw_2;

import java.util.Scanner;

public class task_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] intArray = new int[10];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i;
        }

        try {
            System.out.println("Введите число: ");
            int d = sc.nextInt();
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
        sc.close();
    }
}