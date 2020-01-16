package org.daydevjv.jdbcintegr.utils;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextDouble();
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextInt();
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return SCANNER.next();
    }

    public static String getLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }
}
