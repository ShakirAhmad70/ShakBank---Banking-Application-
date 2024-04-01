package shakbank.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ContactNoValidator {
    private static final String CONTACT_NO_PATTERN = "^[6-9]\\d{9}$";
    private static final Pattern pattern = Pattern.compile(CONTACT_NO_PATTERN);

    private ContactNoValidator() {
    }

    public static boolean validate(final String contactNo) {
        Matcher matcher = pattern.matcher(contactNo);
        return !matcher.matches();
    }
}
