package rklab.utility.utilities;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static rklab.utility.constants.GlobalConstants.DateFormats.YYYYMMDD;

@UtilityClass
public class IdGenerator {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RANDOM_STRING_LENGTH = 10;
    private static final Random RANDOM = new Random();

    public static String generateId() {
        var retVal = new StringBuilder();
        retVal.append(LocalDate.now().format(DateTimeFormatter.ofPattern(YYYYMMDD)));
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int index = RANDOM.nextInt(ALPHANUMERIC.length());
            retVal.append(ALPHANUMERIC.charAt(index));
        }
        return retVal.toString();
    }
}
