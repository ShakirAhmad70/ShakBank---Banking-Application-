package shakbank.beans;

import java.io.Serializable;

@SuppressWarnings("unused")
public class User implements Serializable {
    public String accountHolderName, fatherName, accountNumber, password, email, contactNo;
    public Address address;
    public AccountType accountType;
    public double balance;


    public User(String accountHolderName, String fatherName, String password, String email, String contactNo, Address address, AccountType accountType, double balance) {
        this.accountHolderName = accountHolderName;
        this.fatherName = fatherName;
        this.accountNumber = generateAccountNumber();
        this.password = password;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.accountType = accountType;
        this.balance = balance;
    }

    private String generateAccountNumber() {
        return "SHAK" + System.currentTimeMillis();
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  "accountHolderName => '" + accountHolderName + "'\n" +
                "fatherName => '" + fatherName + "'\n" +
                "accountNumber => '" + accountNumber + "'\n" +
                "password => '" + password + "'\n" +
                "email => '" + email + "'\n" +
                "contactNo => '" + contactNo + "'\n" +
                "address => " + address + "\n" +
                "accountType => '" + accountType + "'\n" +
                "balance => '" + balance + "'\n";
    }
}