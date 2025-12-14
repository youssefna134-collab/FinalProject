package org.youssefn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    /**
     * checks if a postal code is valid: needs to have a length of 6 and needs
     * to follow a structure of ABABAB where A is a character and B is a digit
     * @param postalCode the postal code input
     * @return true if postal code is valid and false if it is not
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }

        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "1234567890";

        for (int i = 0; i <= postalCode.length(); i++) {
            char currentChar = postalCode.charAt(i);
            String charString = String.valueOf(currentChar);
            if (i % 2 == 0) {
                if (letters.contains(charString)) {
                    return false;
                }
            }
            else {
                if (digits.contains(charString))
                    return false;
                }
            }
        return true;
    }

    public enum Province {
        AB, MB, ON, QC, SK, BC, NB, NS, PEI, NL
    }

    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();

        } else {
            this.streetNo = -1;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNo=" + streetNo +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", province=" + province +
                ", postalCode='" + postalCode.toUpperCase() + '\'' +
                '}';
    }
}
