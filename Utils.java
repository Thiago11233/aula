package br.uni.lab.util;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }
}
