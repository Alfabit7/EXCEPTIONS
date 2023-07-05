package hw_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;

public class final_work {
    static Scanner sc = new Scanner(System.in, "cp866");
    static String[] arrDataUser; // в массив записываются данные которые вводит пользователь
    static String uniqueID; // id юзеров
    static String date; // дата создания
    static String[] resArrDataUser = new String[6]; // результирующий массив в котором будет id дата создания
    static Boolean exit = true;
    static String strDataUser;

    public static void main(String[] args) throws checkedInput {
        // метод menu зацикливает запрос ввода данных пользователем если пользователь
        // введ q работа программы прекращается
        while (exit) {
            menu();
        }
    }

    public static String menu() {
        // requestDataUser запрашиваем ввод данных у пользователя в строку
        do {
            strDataUser = requestDataUser();
            // Если пользователь ввёл q или Q, то прекращаем работу программы

            // DRY
            if (strDataUser.equalsIgnoreCase("Q") || strDataUser.equalsIgnoreCase("q")) {
                exit = false; // условие выхода из while в методе main
                return "Q";
            }
            // метод convertStrToArr преобразует строку в массив
            arrDataUser = convertStrToArr(strDataUser);
            // Проверяем что массив arrDataUser не меньше и небольше 4 элементов ФИО и номер
            // телефона если нет перезапускаем метод запроса ввода данных requestDataUser
            while (arrDataUser.length != 4) {
                System.out.println();
                System.out.println("Введено больше или меньше четырёх строк, повторите ввод!");
                strDataUser = requestDataUser();

                // DRY
                if (strDataUser.equalsIgnoreCase("Q") || strDataUser.equalsIgnoreCase("q")) {
                    exit = false; // условие выхода из while в методе main
                    return "Q";
                }
                arrDataUser = convertStrToArr(strDataUser);
            }
            System.out.println();

            // Функция checkedElementArray проверят валидность данных в массиве и возвращает
            // boolean type
        } while (!checkedElementArray(arrDataUser));

        // Если данные валидны создаем id и дату создания пользователя и добавляем в
        // новый массив resArrDataUser
        uniqueID = UUID.randomUUID().toString();
        date = LocalDateTime.now()
                .toString();
        // Записываем даннные из старого массива в новый
        for (int i = 0; i < arrDataUser.length; i++) {
            resArrDataUser[i] = arrDataUser[i];
        }
        // Добавляем id и дату
        resArrDataUser[4] = uniqueID;
        resArrDataUser[5] = date;
        // Функция записи введеных пользователем данных в файл
        writeTofile(resArrDataUser);
        return "Файл сохранен";
    }

    // Метод requestDataUser запрашивает ввод данных у пользователя
    static public String requestDataUser() {
        System.out.print("Введите Фамилию Имя Отчество и номер телефона через пробел. Для выхода нажмите Q или q: ");
        String str = null;
        while (str == null || str.trim().isEmpty()) {
            // пока строка пустая или содержит пробелы будем выбрасывать собственное
            // исключение checkedInput
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

        str = str.stripLeading();
        // удаляем пробелы в начале строки чтобы в массиве образованной из строки не
        // было пустых элементов
        str = str.replaceAll("\\s+", " ");
        // заменяем лишние пробелы на один пробел
        return str;
    }

    static public String[] convertStrToArr(String strDataUser) {
        // Функция конвертирует строку введеную пользователем в массив на 4 элемента ФИО
        // и номер тел.
        arrDataUser = strDataUser.split(" ");
        return arrDataUser;
    }

    // Проверка валидности данных - что первые три элемента это строка, а четвертый
    // это 11-ть цифр
    static public boolean checkedElementArray(String[] arr) {

        // 'эта регулярка Кирилицу не проверяет
        if (arr[0].matches("^[\\p{L}]+$") && arr[1].matches("^[\\p{L}]+$")
                && arr[2].matches("^[\\p{L}]+$") &&
                arr[3].matches("^((\\+7|7|8)+([0-9]){10})$")) {
        } else {
            System.out.println(
                    "Строки ФИО не могут содержать цифры, а номер телефона нужно вводить в формате 89112223344 или 79112223344");
            return false;
        }
        System.out.println("Данные корректны и будут сохранены");
        return true;

        // второй метод регуляркb проверяет кирилиццу Кирилицу не проверяет
        /*
         * String regex = "^[a-zA-Zа-яА-Я]+$";
         * Pattern pattern = Pattern.compile(regex);
         * Matcher matcher = pattern.matcher(arr[0]);
         * Matcher matcher1 = pattern.matcher(arr[1]);
         * Matcher matcher2 = pattern.matcher(arr[2]);
         * if (matcher.find() && matcher1.find() && matcher2.find() &&
         * arr[3].matches("^((\\+7|7|8)+([0-9]){10})$")) {
         * // Проверка для русских символов
         * System.out.println("Данные корректны и будут сохранены");
         * return true;
         * } else {
         * // Проверка для английских символов
         * if (!matcher.find() && !matcher1.find() && !matcher2.find()
         * && arr[3].matches("^((\\+7|7|8)+([0-9]){10})$")) {
         * System.out.println("Данные корректны и будут сохранены");
         * System.out.println(Charset.defaultCharset().displayName());
         * return true;
         * }
         * 
         * }
         * 
         * System.out.
         * println("Строки ФИО не могут содержать цифры, а номер телефона нужно вводить в формате 89112223344"
         * );
         * return false;
         */
    }

    // Исключение - проверка, что строка не пустая или не введены пробелы +
    static class checkedInput extends Exception {
        public checkedInput(String message) {
            super(message);
        }

    }

    // Функция записи в файл
    static public void writeTofile(String[] arrDataUser) {
        boolean fileExists = new File("baseUsers.csv").exists();
        try (FileWriter writer = new FileWriter("baseUsers.csv", true)) {// true чтобы дозаписывать файл
            String[] columnNames = { "Surname", "Name", "Lastname", "ID", "Phone_number",
                    "Date" };// Названия столбцов в файле csv

            if (!fileExists) {
                StringBuilder sb = new StringBuilder();
                // проверяем существует файл или нет если существует, то название столбцов не
                // добавляем
                for (String columnName : columnNames) {
                    sb.append(columnName).append(";");
                }
                sb.deleteCharAt(sb.length() - 1); // Удаляем последюю запятую
                sb.append("\n"); // в конце записи делаем перенос на новую строку
                writer.write(sb.toString());
            }

            StringBuilder sb = new StringBuilder();
            for (String str : arrDataUser) {
                // разделитель ";" для записи в разные столбцы. Для записи в один использовать
                // разделитель ","
                sb.append(str).append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
            // Удаляем последюю запятую
            sb.append("\n");
            // в конце записи делаем перенос на новую строку
            writer.write(sb.toString());
        } catch (

        IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
            System.out.println("Закройте файл и попробуйте снова");
        }
    }

    // Второй метод слохранения в файл
    /*
     * 
     * public static void writeTofile(String[] arrDataUser) {
     * boolean fileExists = new File("baseUsers.csv").exists();
     * 
     * try (FileOutputStream fos = new FileOutputStream("baseUsers.csv", true);
     * OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
     * BufferedWriter writer = new BufferedWriter(osw)) {
     * 
     * String[] columnNames = { "Фамилия", "Имя", "Отчество", "ID",
     * "Номер телефона", "Дата" };
     * 
     * if (!fileExists) {
     * StringBuilder sb = new StringBuilder();
     * 
     * for (String columnName : columnNames) {
     * sb.append(columnName).append(";");
     * }
     * 
     * sb.deleteCharAt(sb.length() - 1);
     * sb.append("\n");
     * 
     * writer.write(sb.toString());
     * }
     * 
     * StringBuilder sb = new StringBuilder();
     * for (String str : arrDataUser) {
     * sb.append(str).append(";");
     * }
     * sb.deleteCharAt(sb.length() - 1);
     * sb.append("\n");
     * 
     * writer.write(sb.toString());
     * } catch (IOException e) {
     * e.printStackTrace();
     * System.out.println("Закройте файл и попробуйте снова");
     * }
     * }
     */
}
