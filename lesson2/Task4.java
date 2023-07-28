// Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = readString(scanner);
        System.out.println("Введенная строка: " + str);
    }

    public static String readString(Scanner scanner) {
        while (true) {
            try {
                String str = scanner.nextLine();
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("Ошибка: пустые строки вводить нельзя");
                } else {
                    return str;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}