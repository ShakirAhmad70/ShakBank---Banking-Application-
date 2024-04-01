package shakbank.beans;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Address implements Serializable {
    public String village, city, state, country, pinCode;

    public Address(String village, String city, String state, String country, String pinCode) {
        this.village = village;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return  "\n\t\t\tvillage -> '" + village + "'\n" +
                "\t\t\tcity -> '" + city + "'\n" +
                "\t\t\tstate -> '" + state + "'\n" +
                "\t\t\tcountry -> '" + country + "'\n" +
                "\t\t\tpinCode -> '" + pinCode + '\'';
    }
}