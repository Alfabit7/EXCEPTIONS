/*
 * Разработайте программу, которая выбросит Exception, когда пользователь вводит
 * пустую строку. Пользователю должно показаться сообщение, что пустые строки
 * вводить нельзя.
 */

package hw_2;

import java.util.Scanner;

public class task_4 {
    public static void main(String[] args) throws myException {
        System.out.print("Введите число: ");
        String mes = checkedInputUser();
        System.out.println(mes);
    }

    static String checkedInputUser() throws myException {
        String res = null;
        Scanner sc = new Scanner(System.in);
        while (res == null || res.trim().isEmpty()) {
            try {
                res = sc.nextLine();
                if (res.trim().isEmpty()) {
                    throw new myException("Пустая строрка, повторите ввод!");
                }
            } catch (myException e) {
                System.out.println(e.getMessage());
                System.out.print("Повторите ввод: ");
            }
        }
        return res;
    }

    static class myException extends Exception {
        public myException(String message) {
            super(message);
        }
    }
}