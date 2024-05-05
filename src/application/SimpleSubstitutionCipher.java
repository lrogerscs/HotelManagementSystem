package application;

import java.util.HashMap;
import java.util.Map;

public class SimpleSubstitutionCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String SUBSTITUTION = "bcdefghijklmnopqrstuvwxyzaBCDEFGHIJKLMNOPQRSTUVWXYZA9876543210";

    private static final Map<Character, Character> encryptMap = new HashMap<>();
    private static final Map<Character, Character> decryptMap = new HashMap<>();

    static {
        for (int i = 0; i < ALPHABET.length(); i++) {
            encryptMap.put(ALPHABET.charAt(i), SUBSTITUTION.charAt(i));
            decryptMap.put(SUBSTITUTION.charAt(i), ALPHABET.charAt(i));
        }
    }

    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (encryptMap.containsKey(c)) {
                result.append(encryptMap.get(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (decryptMap.containsKey(c)) {
                result.append(decryptMap.get(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
