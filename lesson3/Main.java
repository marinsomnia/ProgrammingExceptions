import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите ФИО, дату рождения, номер телефона, пол:");
            String data = scanner.nextLine();
            DataPerson(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void DataPerson(String data) throws Exception {
        String[] parts = data.split(" ");

        if (parts.length < 6) {
            throw new Exception("Вы ввели меньше данных, чем требуется.");
        } else if (parts.length > 6) {
            throw new Exception("Вы ввели больше данных, чем требуется.");
        }

        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDate = parts[3];
        String phoneNumber = parts[4];
        String gender = parts[5];

        // Проверка формата даты

        SimpleDateFormat df = new SimpleDateFormat("dd.mm.yyyy"); //Создается объект SimpleDateFormat, который определяет формат даты, в котором ожидается ввод пользователя.   
        df.setLenient(false);                                    // Этот метод делает интерпретацию даты более строгой.
        Date javaDate;
        try {            
            javaDate = df.parse(birthDate);  // Если строка не соответствует формату "dd.mm.yyyy" или содержит недопустимые значения, то будет выброшено исключение, и выполнение программы перейдет в блок catch
                
           Calendar cal = Calendar.getInstance();  // Создается объект Calendar, который представляет определенный момент времени.
           cal.setTime(javaDate);    // Устанавливает время календаря, равное преобразованной дате.

            if (cal.get(Calendar.DAY_OF_MONTH) > cal.getActualMaximum(Calendar.DAY_OF_MONTH) ||   // Используем данное условие, чтобы проверить правильность дня, месяца, года
                cal.get(Calendar.MONTH) > 11 || cal.get(Calendar.YEAR) > 2023) {
                throw new Exception("Такой даты не существует");
            }
        }  catch (Exception e) {
            throw new Exception("Такой даты не существует");
        }

        // Проверка номера телефона
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new Exception("Номер телефона должен быть целым числом.");
        }

        // Проверка пола
        if (!gender.equals("m") && !gender.equals("f")) {
            throw new Exception("Пол должен быть 'm' или 'f'.");
        }

        // Запись в файл
        try {
            File file = new File(lastName + ".txt");
            FileWriter fw = new FileWriter(file, true); // Создаем FileWriter с параметром append равным true, чтобы происходила дозапись в файл
            PrintWriter out = new PrintWriter(fw);
            out.println(lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender);
            out.close();
        } catch (FileNotFoundException e) {
            throw new Exception("Проблема с записью в файл: " + e.getMessage());
        }
    }
}