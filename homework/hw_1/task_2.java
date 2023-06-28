/*Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив, каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя. */

package hw_1;

import java.util.Random;
// import javax.management.RuntimeErrorException;

public class task_2 {
    static Random rnd = new Random();

    public static void main(String[] args) {
        int[] arr_1 = generateArray();
        int[] arr_2 = generateArray();
        System.out.print("ARR_1= ");
        for (int i = 0; i < arr_1.length; i++) {
            System.out.print(arr_1[i]);
        }
        System.out.println();
        System.out.print("ARR_2= ");
        for (int i = 0; i < arr_2.length; i++) {
            System.out.print(arr_2[i]);
        }

        int[] newArr = calcArr(arr_1, arr_2);
        if (newArr != null) {
            System.out.println();
            System.out.println("NewARR");
            for (int i = 0; i < newArr.length; i++) {
                System.out.print(newArr[i] + " ");
            }
        }
    }

    // генерация массива длиной от 2 до 7
    static int[] generateArray() {
        int[] arr = new int[rnd.nextInt(5) + 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(10);
        }
        return arr;
    }

    static int[] calcArr(int[] arr_1, int[] arr_2) {
        /*
         * метод считает разность элементов массивов, если их размерность одинаковая,
         */
        int[] newArr = new int[arr_1.length];
        if (arr_1.length == arr_2.length) {
            System.out.println();
            for (int i = 0; i < arr_1.length; i++) {
                newArr[i] = arr_1[i] - arr_2[i];
            }
        } else {
            System.out.println();
            System.out.println("массивы разной размерности");
            newArr = null;
        }
        return newArr;
    }

}
