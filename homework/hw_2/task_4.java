/*
 * Разработайте программу, которая выбросит Exception, когда пользователь вводит
 * пустую строку. Пользователю должно показаться сообщение, что пустые строки
 * вводить нельзя.
 */

package hw_2;

import java.util.Scanner;
import javax.management.RuntimeErrorException;

public class task_4 {
    public static void main(String[] args) {
        String mes = checkedInputUser();
        System.out.println(mes);
    }

    static String checkedInputUser() {

        String res;
        Scanner sc = new Scanner(System.in);

        try {
            res = sc.nextLine();
            if (res.isEmpty() || res == null) {
                // sc.next();
                throw new RuntimeErrorException(null, "Строка недолжна быть пустой повторите ввод: ");

            } else {

            }

        } catch (RuntimeErrorException e) {
            // sc.next();
            e.printStackTrace();
            e.getMessage();

            return null;
        }
        return res;

    }

    class myExeption extends Exception {
        private String message;

        public myExeption() {
            this.message = "!!!!!";
        }
    }
}
