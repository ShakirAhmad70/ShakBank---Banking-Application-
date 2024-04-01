package shakbank.beans;

import java.io.Serializable;

@SuppressWarnings("unused")
public enum AccountType implements Serializable {
    SAVING,
    CURRENT,
    FIXED
}