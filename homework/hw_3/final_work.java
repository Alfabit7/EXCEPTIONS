package hw_3;

import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class final_work {
    static Scanner sc = new Scanner(System.in);
    static String[] arrDataUser;

    public static void main(String[] args) throws checkedInput {
        // запрашиваем ввод данных у пользователя в строку
        do {
            String strDataUser = requestDataUser();
            // Преобразуем строку в массив
            arrDataUser = convertStrToArr(strDataUser);

            // Проверяем что массив не меньше 4 элементов ФИО и номер телефона
            while (arrDataUser.length != 4) {
                System.out.println();
                System.out.println("Введено больше или меньше четырёх строк, повторите ввод!");
                strDataUser = requestDataUser();
                arrDataUser = convertStrToArr(strDataUser);
            }

            System.out.println();
            // Функция проверят какие данные в массиве
            if (checkedElementArray(arrDataUser)) {
                System.out.println();
                // Выводим массив
                System.out.print("ARR: ");
                for (int i = 0; i < arrDataUser.length; i++) {
                    System.out.print(arrDataUser[i] + " ");
                }

            } else {
                System.out.println();
                System.out.println("Повторите ввод");
                strDataUser = requestDataUser();
            }

        } while (!checkedElementArray(arrDataUser));
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
        // удаляем пробелы в начале строки чтобы в массиве образованной из строки не
        // было пустых элементов
        str = str.stripLeading();
        // заменяем лишние пробелы на один пробел
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    // Функция конвертирует строку в массив на 4 элемента ФИО и номер тел.
    static public String[] convertStrToArr(String strDataUser) {
        arrDataUser = strDataUser.split(" ");
        return arrDataUser;
    }

    // Проверка на то что первые три элемента это строка, а четвертый это 11-ть цифр
    static public boolean checkedElementArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[0].matches("[a-zA-ZА-яЁё]+") && arr[1].matches("[a-zA-ZА-яЁё]+")
                    && arr[2].matches("[a-zA-ZА-яЁё]+") && arr[3].matches("^((\\+7|7|8)+([0-9]){10})$")) {
                System.out.println(" OK");
                return true;
            } else {
                System.out.println(
                        "Строки ФИО не могут содержать цифры, а номер телефона нужно вводить в формате 89112223344");
                return false;
            }
        }
        return true;
    }

    // Проверка, что строка не пустая или не введены пробелы +
    static class checkedInput extends Exception {
        public checkedInput(String message) {
            super(message);
        }
    }

}

/*
 * Проверка на наличие только букв русского и английского афавита
 * class Main
 * {
 * public static boolean isAlpha(String s)
 * {
 * if (s == null) {
 * return false;
 * }
 * 
 * for (int i = 0; i < s.length(); i++)
 * {
 * char c = s.charAt(i);
 * if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c >= 'А' && c <=
 * 'Я') && !(c >= 'а' && c <= 'я')) {
 * return false;
 * }
 * }
 * return true;
 * }
 * 
 * public static void main(String[] args)
 * {
 * String s = "БяABCD";
 * System.out.println("IsAlpha: " + isAlpha(s));
 * }
 * }
 */