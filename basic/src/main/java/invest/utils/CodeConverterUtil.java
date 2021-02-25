package invest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeConverterUtil {
    public static String convertCode(String code) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        return m.replaceAll("").trim();
    }
}
