package simpleEncryptDecrypt;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class SimpleEncryptDecrypt 
{
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 128;

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            System.out.println("Choose an option:");
            System.out.println("1. Encrypt File");
            System.out.println("2. Decrypt File");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    encryptFile(scanner);
                    break;
                case 2:
                    decryptFile(scanner);
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void encryptFile(Scanner scanner) 
    {
        try 
        {
            System.out.print("Enter the input file path (relative to the program's working directory): ");
            String inputFileRelative = scanner.nextLine();
            System.out.print("Enter the output file path (relative to the program's working directory): ");
            String outputFileRelative = scanner.nextLine();
            System.out.print("Enter a secret key (16 characters): ");
            String secretKey = scanner.nextLine();

            // Obtain the program's working directory
            String baseDirectory = System.getProperty("user.dir");

            // Construct absolute paths by concatenating the base directory with relative paths
            String inputFileAbsolutePath = baseDirectory + File.separator + inputFileRelative;
            String outputFileAbsolutePath = baseDirectory + File.separator + outputFileRelative;

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(secretKey));

            FileInputStream inputStream = new FileInputStream(inputFileAbsolutePath);
            FileOutputStream outputStream = new FileOutputStream(outputFileAbsolutePath);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) 
            {
                cipherOutputStream.write(buffer, 0, bytesRead);
            }

            cipherOutputStream.close();
            inputStream.close();

            System.out.println("File encrypted successfully.");
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println("File not found: " + e.getMessage());
        } 
        catch (Exception e) 
        {
            System.err.println("Encryption error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void decryptFile(Scanner scanner) 
    {
        try 
        {
            System.out.print("Enter the input (encrypted) file path (relative to the program's working directory): ");
            String inputFileRelative = scanner.nextLine();
            System.out.print("Enter the input (decrypted) file path (relative to the program's working directory): ");
            String outputFileRelative = scanner.nextLine();
            System.out.print("Enter the secret key used for encryption: ");
            String secretKey = scanner.nextLine();

            // Obtain the program's working directory
            String baseDirectory = System.getProperty("user.dir");

            // Construct absolute paths by concatenating the base directory with relative paths
            String inputFileAbsolutePath = baseDirectory + File.separator + inputFileRelative;
            String outputFileAbsolutePath = baseDirectory + File.separator + outputFileRelative;

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, generateSecretKey(secretKey));

            FileInputStream inputStream = new FileInputStream(inputFileAbsolutePath);
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            FileOutputStream outputStream = new FileOutputStream(outputFileAbsolutePath);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = cipherInputStream.read(buffer)) != -1) 
            {
                outputStream.write(buffer, 0, bytesRead);
            }

            cipherInputStream.close();
            outputStream.close();

            System.out.println("File decrypted successfully.");
        } 
        catch (Exception e) 
        {
            System.err.println("Decryption error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static SecretKey generateSecretKey(String key) throws NoSuchAlgorithmException 
    {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] keyBytes = key.getBytes();
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, KEY_SIZE / 8);

        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}
