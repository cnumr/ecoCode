package fr.cnumr.java.utils;

public class GoodWayConcatenateStringsLoop {

    public String concatenateStrings(String[] strings) {
        StringBuilder result = new StringBuilder();

        for (String string : strings) {
            result.append(string);
        }
        return result.toString();
    }

}
