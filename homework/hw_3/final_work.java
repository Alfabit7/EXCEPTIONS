package hw_3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class final_work {
    static Scanner sc = new Scanner(System.in);
    static String[] arrDataUser; // в массив записываются данные которые вводит пользователь
    static String uniqueID; // id юзеров
    static String date; // дата создания
    static String[] resArrDataUser = new String[6]; // результирующий массив в котором будет id дата создания

    public static void main(String[] args) throws checkedInput {
        // requestDataUser запрашиваем ввод данных у пользователя в строку
        do {
            String strDataUser = requestDataUser();
            // convertStrToArr Преобразуем строку в массив
            arrDataUser = convertStrToArr(strDataUser);

            // Проверяем что массив arrDataUser не меньше и небольше 4 элементов ФИО и номер
            // телефона
            while (arrDataUser.length != 4) {
                System.out.println();
                System.out.println("Введено больше или меньше четырёх строк, повторите ввод!");
                strDataUser = requestDataUser();
                arrDataUser = convertStrToArr(strDataUser);
            }

            System.out.println();
            // Функция checkedElementArray проверят валидность данных в массиве boolean type
        } while (!checkedElementArray(arrDataUser));
        // Если данные валидны создаем id и дату создания пользователя и добавляем в
        // массив
        uniqueID = UUID.randomUUID().toString();
        date = LocalDateTime.now()
                .toString();
        for (int i = 0; i < arrDataUser.length; i++) {
            resArrDataUser[i] = arrDataUser[i];
        }
        resArrDataUser[4] = uniqueID;
        resArrDataUser[5] = date;
        // Функция записи введеных пользователем данных в файл
        writeTofile(resArrDataUser);
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

        str = str.stripLeading();
        // удаляем пробелы в начале строки чтобы в массиве образованной из строки не
        // было пустых элементов
        str = str.replaceAll("\\s+", " ");
        // заменяем лишние пробелы на один пробел
        return str;
    }

    static public String[] convertStrToArr(String strDataUser) {
        // Функция конвертирует строку в массив на 4 элемента ФИО и номер тел.
        arrDataUser = strDataUser.split(" ");
        return arrDataUser;
    }

    // Проверка валидности данных - что первые три элемента это строка, а четвертый
    // это 11-ть цифр
    static public boolean checkedElementArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[0].matches("[a-zA-ZА-яЁё]+") && arr[1].matches("[a-zA-ZА-яЁё]+")
                    && arr[2].matches("[a-zA-ZА-яЁё]+") && arr[3].matches("^((\\+7|7|8)+([0-9]){10})$")) {
                System.out.println("Данные корректны и будут сохранены");
                return true;
            } else {
                System.out.println(
                        "Строки ФИО не могут содержать цифры, а номер телефона нужно вводить в формате 89112223344");
                return false;
            }
        }
        return true;
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
            String[] columnNames = { "Surname", "Name", "Lastname", "ID", "Phone_number", "Date" };

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
}
