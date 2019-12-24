package org.daydevjv.jdbcintegr.utils;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
}
