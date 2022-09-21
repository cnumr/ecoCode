package fr.cnumr.java.utils;

public class AvoidConcatenateStringsInLoop {

    public String concatenateStrings(String[] strings) {
        String result = "";

        for (String string : strings) {
            result += string; // Noncompliant
        }
        return result;
    }

    public String concatenateStrings2() {
        String result = "";

        for (int i = 0; i < 1000; ++i) {
            result += "another"; // Noncompliant
        }
        return result;
    }

}
