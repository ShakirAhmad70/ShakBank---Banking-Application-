package shakbank.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordValidator {
    //password must be of minimum 8 characters and must have a capital letter and a small letter and digits and does not contain spaces
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z])(?=.*\\d)(?!.*\\s).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private PasswordValidator(){
    }
    public static boolean validate(final String password) {
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }
}
