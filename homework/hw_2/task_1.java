package hw_2;

import java.util.Scanner;

/*Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных. */
public class task_1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        float res;
        do {
            res = getInputNumber();
        } while (res == -1);

        sc.close();
        System.out.println(res);
    }

    static float getInputNumber() {
        float a;

        try {
            if (sc.hasNextFloat()) {
                a = sc.nextFloat();
                return a;
            } else {
                System.out.println("Нужно ввести дробное число (с запятой) ");
                sc.next(); // очищаем буфер ввода
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Нужно ввести дробное число (с запятой) ");
            sc.next(); // очищаем буфер ввода
            return -1;
        }
    }
}
