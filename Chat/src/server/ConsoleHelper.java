package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static String readString() {

        String message;
        while (true) {
            try {
                message = bufRead.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                readString();
            }
        }
        return message;
    }
    public static int readInt() {
        int i;
        while (true) {
            try {
                i = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                Integer.parseInt(readString());
            }
        }
        return i;
    }

}
