package com.app.service;

import java.math.BigDecimal;
import java.util.Scanner;

public final class UserDataService {

    private UserDataService() {}

    public static String getString() {
        return new Scanner(System.in).nextLine();
    }

    public static BigDecimal getBigDecimal() {
        return new Scanner(System.in).nextBigDecimal();
    }

    public static Double getDouble() {
        return new Scanner(System.in).nextDouble();
    }

    public static Integer getInteger() {
        return new Scanner(System.in).nextInt();
    }
}
