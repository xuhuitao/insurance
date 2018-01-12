package net.rokyinfo.insurance.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatUtils {

    public static final String TWO_DECIMAL = "#.00";

    public static String formatDecimal(BigDecimal bigDecimal, String format) {
        if (bigDecimal == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(bigDecimal.doubleValue());
    }

}
