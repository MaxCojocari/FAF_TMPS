package cryptography;

import java.security.MessageDigest;
import java.util.Random;

public abstract class HashGenerator {
    public static String getZeroHash() {
        return "0x0000000000000000000000000000000000000000";
    }

    public static String computeSha1Hash(String input) {
        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-1");
            byte[] hash = SHA256.digest(input.getBytes("UTF-8"));
            return getHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String computeSha256Hash(String input) {
        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = SHA256.digest(input.getBytes("UTF-8"));
            return getHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomAddress(int numchars) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(random.nextInt()));
        }

        return "0x" + sb.toString().substring(0, numchars);
    }

    private static String getHexString(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
