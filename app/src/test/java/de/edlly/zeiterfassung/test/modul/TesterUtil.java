package de.edlly.zeiterfassung.test.modul;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TesterUtil {

    static boolean regExDatumString(String datum) {

        Pattern p = Pattern.compile("^(31|30|[012]\\d|\\d)[./](0\\d|1[012]|\\d)[./](\\d{4}|\\d\\d) ([01]?\\d|2[0-3]):([0-5]?\\d)$");
        Matcher m = p.matcher(datum);
        return m.matches();
    }
}
