package com.epam.petersburg.ncr41.executors;

import com.epam.petersburg.ncr41.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordHashing {

    private static final String HASH_ALGORITHM = "SHA1PRNG";
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;

    private static final Logger LOGGER = LogManager.getLogger(PasswordHashing.class);

    public static String hashPass(char[] password) {
        try {
            SecureRandom random = SecureRandom.getInstance(HASH_ALGORITHM);
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            return toHex(salt) + ":" + toHex(hashSimple(password, salt));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }

    public static boolean validatePass(char[] pass, String passwordHash) {
        try {
            String[] parts = passwordHash.split(":");
            byte[] salt = fromHex(parts[0]);
            byte[] hash = fromHex(parts[1]);
            byte[] testHash = hashSimple(pass, salt);
            return Arrays.equals(hash, testHash);
        } catch (InvalidKeySpecException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    private static byte[] hashSimple(char[] password, byte[] salt) throws InvalidKeySpecException {
        byte[] result = null;
        KeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
            result = f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
