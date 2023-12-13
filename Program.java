import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String[]> list = readData(3);
        checkFormatData(list);
        fileWrite(list);

    }

    public static List<String[]> readData(int number) {
        List<String[]> list = new ArrayList<>();
        System.out.println("Введите через пробел фамилию, имя, отчество, дату рождения (в формате dd.mm.yyyy), " +
                    "номер телефона (формат-10 цифр без пробелов), пол (формат f или m)");
        for (int i = 0; i < number; i++) {
            String line = scanner.nextLine();
            list.add(line.split(" "));
            if (list.get(i).length != 6) {
                throw new IllegalArgumentException(String.format("Требуется ввести 6 элементов, вы ввели %d! " +
                                "Перезапустите программу!",
                        list.get(i).length ));
                }
            }
        return list;
    }

    public static void checkFormatData(List<String[]> list) {
        checkFormatDate(list);
        checkFormatNumberPhone(list);
        checkFormatGender(list);
    }

    public static void checkFormatDate(List<String[]> list){
        try {
            for (String[] el : list) {
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                format.parse(el[3]);
            }
        } catch (ParseException e) {
            System.out.println("Неверный формат даты рождения! Перезапустите программу и введите дату рождения в " +
                    "формате dd.mm.yyyy! ");
        }
    }

    public static void checkFormatNumberPhone(List<String[]> list){
        for (String[] el : list) {
            if (el[4].length() != 10)
                throw new NumberFormatException("Номер телефона введен некорректно! Перезапустите программу и введите " +
                        "телефон верно (формат-10 цифр без пробелов)!");
            try {
                Long.parseLong(el[4]);
            }
            catch (NumberFormatException e) {
                System.out.println("Номер телефона введен некорректно! Перезапустите программу и введите телефон верно " +
                        "(формат-10 цифр без пробелов)!");
            }
        }
    }

    public static void checkFormatGender(List<String[]> list){
        for (String[] el : list) {
            if (!el[5].toLowerCase().equals("f") && !el[5].toLowerCase().equals("m"))
                throw new IllegalArgumentException("Пол указан неверно! Перезапустите программу и введите пол " +
                        "(формат f или m)");

        }
    }

    public static void fileWrite(List<String[]> list){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt", false))) {
            for (String[] el: list) {
                writer.write(String.join(" ", el[0], el[1], el[2], el[3], el[4], el[5]) + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
