package rklab.utility.constants;

/**
 * This class contains all the constants that are observed to be user across repository and are common
 * This can grow incrementally as some of the constants are observed to be common.
 *
 * Note: This class should only contain constants and not any code logic
 */

public class GlobalConstants {

    public static String ERROR_OCCURRED = "Error: {}";
    public static String TOKEN = "token";
    public static String EMPTY_STRING = "";

    // the below class only stores the symbols constants
    public static class Symbols{

        public static String DOUBLE_COLON = "::";
        public static String COMMA = ",";
        public static String PERCENTAGE = "%";

    }

    public static class DateFormats{

        public static String YYYYMMDD = "yyyyMMdd";

    }

    public static class RegexPattern{
        public static final String IFSC_REGEX = "^[A-Z]{4}0[A-Z0-9]{6}$";
        public static final String PHONE_NO_REGEX = "^[6-9]\\d{9}$";
        public static final String PIN_CODE_REGEX = "^[1-9][0-9]{5}$";
    }

}
