package com.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;

public class FolderEncryptor {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static void encryptFolder(File folder, String password) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        encryptFolder(file, password);
                    } else {
                        encryptFile(file, password);
                    }
                }
            }
        }
    }

    public static void decryptFolder(File folder, String password) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        decryptFolder(file, password);
                    } else {
                        decryptFile(file, password);
                    }
                }
            }
        }
    }

    private static void encryptFile(File file, String password) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();

            byte[] encryptedBytes = encrypt(fileBytes, password);

            File encryptedFile = new File(file.getAbsolutePath() + ".hidden");
            FileOutputStream outputStream = new FileOutputStream(encryptedFile);
            outputStream.write(encryptedBytes);
            outputStream.close();

            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(File file, String password) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();

            byte[] decryptedBytes = decrypt(fileBytes, password);

            File decryptedFile = new File(file.getAbsolutePath().replace(".hidden", ""));
            FileOutputStream outputStream = new FileOutputStream(decryptedFile);
            outputStream.write(decryptedBytes);
            outputStream.close();

            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] encrypt(byte[] input, String password) {
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] input, String password) {
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Key generateKey(String password) {
        try {
            byte[] passwordBytes = Arrays.copyOf(password.getBytes(StandardCharsets.UTF_8), 32);
            return new SecretKeySpec(passwordBytes, ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
