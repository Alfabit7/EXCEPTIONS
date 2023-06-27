package hw_1;

import java.util.Random;

/**
 * Реализуйте метод, принимающий в качестве аргументов два целочисленных
 * массива, и возвращающий новый массив, каждый элемент которого равен частному
 * элементов двух входящих массивов в той же ячейке. Если длины массивов не
 * равны, необходимо как-то оповестить пользователя. Важно: При выполнении
 * метода единственное исключение, которое пользователь может увидеть -
 * RuntimeException, т.е. ваше
 */
public class task_3 {
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
        System.out.println();
        System.out.println("NewARR");
        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i] + " ");
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
         * метод считает частное элементов массивов, если их размерность одинаковая,
         * если размерность разная выбрасывает исключение RuntimeException
         */
        int[] newArr = new int[arr_1.length];
        if (arr_1.length != arr_2.length) {
            System.out.println();
            throw new RuntimeException("массивы разной размерности");

        } else {
            System.out.println();
            for (int i = 0; i < arr_1.length; i++) {
                newArr[i] = arr_1[i] / arr_2[i];
            }
        }
        return newArr;
    }
}
