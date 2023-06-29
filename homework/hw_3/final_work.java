package hw_3;

import java.util.Scanner;

public class final_work {
    static Scanner sc = new Scanner(System.in);
    static String[] arrDataUser;

    public static void main(String[] args) throws checkedInput {

        String strDataUser = requestDataUser();
        arrDataUser = convertStrToArr(strDataUser);
        while (arrDataUser.length != 4) {
            System.out.println();
            System.out.println("Введено больше или меньше четырёх строк, повторите ввод!");
            strDataUser = requestDataUser();
            arrDataUser = convertStrToArr(strDataUser);
        }

        // System.out.printf("STR: %s\t", strDataUser);
        // System.out.println();
        System.out.print("ARR: ");
        for (int i = 0; i < arrDataUser.length; i++) {
            System.out.print(arrDataUser[i] + " ");

        }

    }

    static public String[] convertStrToArr(String strDataUser) {
        arrDataUser = strDataUser.split(" ");
        return arrDataUser;
    }

    // Метод запрашивает ввод данных у пользователя
    static public String requestDataUser() {
        System.out.print("Введите Фамилию Имя Отчество и номер телефона через пробел: ");
        String str = null;
        while (str == null || str.trim().isEmpty()) {
            try {
                str = sc.nextLine();
                if (str.trim().isEmpty()) {
                    throw new checkedInput("Вы не ввели данных!");
                }
            } catch (checkedInput e) {
                System.out.println(e.getMessage());
                System.out.print("Повторите ввод! Введите Фамилию Имя Отчество и номер телефона через пробел: ");
            }
        }
        // удаляем пробелы в начале строки чтобы в массиве образованной ищ строки не
        // было пустых элементов
        str = str.stripLeading();
        // заменяем лишние пробелы на один пробел
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    // Проверка что строка не пустая или не введены пробелы +
    static class checkedInput extends Exception {
        public checkedInput(String message) {
            super(message);
        }
    }

    // Проверяет длину массива
    static class checkedLengtArr extends Exception {
        public checkedLengtArr(String mes) {
            super(mes);
        }
    }

}