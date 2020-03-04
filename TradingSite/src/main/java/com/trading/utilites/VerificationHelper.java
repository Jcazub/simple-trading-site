package com.trading.utilites;

import java.math.BigDecimal;

public class VerificationHelper {

    public static boolean isStringInvalid(String string) {
        if (string == null || string.trim().equals("")) return true;
        return false;
    }

    public static boolean isBigDecimalInvalid(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(0)) <= 0) return true;
        return false;
    }
}
