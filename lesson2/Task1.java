// Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение. 
// Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.

import java.util.Scanner;


public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дробное число:");
        float number = readFloat(scanner);
        System.out.println("Введенное число: " + number);
    }

    public static float readFloat(Scanner scanner) {
        while (true) {
            try {
                String str = scanner.nextLine();
                if (!str.contains(".")) {
                    throw new NumberFormatException();
                }
                float number = Float.parseFloat(str);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите дробное число");
            }
        }
    }
}