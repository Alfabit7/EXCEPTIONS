package hw_1;

/*Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?*/
import java.util.ArrayList;
import java.util.Scanner;

public class task_1 {
    public static void main(String[] args) {
        arrExceptions();
        // calcEsxecption();
        // inputExeprtion();

    }

    static void arrExceptions() {
        ArrayList<Integer> arr = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }
        System.out.println(arr.get(10));

    }

    static double calcEsxecption() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("res= " + a / b);
        sc.close();
        return a / b;
    }

    static void inputExeprtion() {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        System.out.println("You input number: " + number);
        sc.close();
    }

}
