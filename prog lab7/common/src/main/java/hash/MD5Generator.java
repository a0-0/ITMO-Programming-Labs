package hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    public static String getHash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hash = new StringBuilder();
            hash.append(no.toString(16));
            while(hash.length() < 32) {
                hash.insert(0, "0");
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }

    }
}
