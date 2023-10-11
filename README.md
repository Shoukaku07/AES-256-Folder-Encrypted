# FolderEncryptor

FolderEncryptor is a Java program designed to provide file encryption and decryption functionality within a folder using the AES (Advanced Encryption Standard) algorithm with a user-specified password. This tool is intended for securing sensitive files on your local storage.

## How It Works

FolderEncryptor operates as follows:

### Encryption Process

1. The `encryptFolder` method is invoked with a folder to encrypt and a user-provided password.
2. It recursively traverses the folder structure.
3. For each file encountered, it reads the file's content into `fileBytes`.
4. The `encrypt` method is called with `fileBytes` and the password.
5. Inside the `encrypt` method, a secret key is generated from the user's password.
6. The AES encryption algorithm is initialized with this secret key.
7. The file content (`fileBytes`) is encrypted using AES encryption, resulting in `encryptedBytes`.
8. The encrypted content is written to a new file with the extension `.hidden`.
9. The original file is securely deleted to prevent data leakage.

### Decryption Process

1. The `decryptFolder` method is invoked with a folder to decrypt and the corresponding password.
2. It recursively traverses the folder structure.
3. For each `.hidden` file encountered, it reads its content into `fileBytes`.
4. The `decrypt` method is called with `fileBytes` and the password.
5. Inside the `decrypt` method, the secret key is generated from the provided password.
6. The AES decryption algorithm is initialized with this secret key.
7. The file content (`fileBytes`) is decrypted using AES decryption, resulting in `decryptedBytes`.
8. The decrypted content is written to a new file with the `.hidden` extension removed.
9. The original `.hidden` file is securely deleted.

### Key Generation

- The `generateKey` method creates a 128-bit (16-byte) secret key from the provided password. This key is used for both encryption and decryption. For AES-256 encryption, ensure that your password is 32 bytes long, and consider implementing a secure key derivation function (KDF) for stronger key generation.

### Encryption Algorithm Details

- AES encryption is performed in ECB mode with PKCS5 padding (`"AES/ECB/PKCS5Padding"`).

## How to use it?

You can download the java file that I have provided. then place them in the respective java src, making sure to change the package according to your application,
Use `compileSdkVersion 32` in build.gradle.

How to start encrypted?

Encrypted folder containing files:
```java
File folderToEncrypt = new File("/storage/emulated/0/MyFolder");
FolderEncryptor.encryptFolder(folderToEncrypt, "MyPassword");
```
Decrypted:
```java
File folderToDecrypt = new File("/storage/emulated/0/MyFolder");
FolderEncryptor.decryptFolder(folderToDecrypt, "MyPassword");
```

How to encrypt 1 file?
```java
File fileToEncrypt = new File("/storage/emulated/0/MyFiles.txt");
String encryptionPassword = "your_password_here";
FolderEncryptor.encryptFile(fileToEncrypt, encryptionPassword);
```
Decrypted:
```java
File decryptFile = new File("/storage/emulated/0/MyFiles.txt");
String decryptPassword = "your_password_here";
FolderEncryptor.decryptFile(decryptFile, decryptPassword);
```

note: You can `custom extension`

### AES custom bit

AES-128:
```java
    private static Key generateKey(String password) { 
         try { 
             byte[] passwordBytes = Arrays.copyOf(password.getBytes(StandardCharsets.UTF_8), 16); 
             return new SecretKeySpec(passwordBytes, ALGORITHM); 
         } catch (Exception e) { 
             e.printStackTrace(); 
         } 
         return null; 
     }
```

AES-192:
```java
    private static Key generateKey(String password) { 
         try { 
             byte[] passwordBytes = Arrays.copyOf(password.getBytes(StandardCharsets.UTF_8), 24); 
             return new SecretKeySpec(passwordBytes, ALGORITHM); 
         } catch (Exception e) { 
             e.printStackTrace(); 
         } 
         return null; 
     }
```

AES-256:
```java
    private static Key generateKey(String password) { 
         try { 
             byte[] passwordBytes = Arrays.copyOf(password.getBytes(StandardCharsets.UTF_8), 32); 
             return new SecretKeySpec(passwordBytes, ALGORITHM); 
         } catch (Exception e) { 
             e.printStackTrace(); 
         } 
         return null; 
     }
```

## Security Considerations

While this code provides a basic example of file encryption and decryption, it is essential to consider additional security measures for real-world applications:

- Securely storing and managing encryption keys to protect against unauthorized access.
- Handling exceptions and errors gracefully to prevent potential vulnerabilities.
- Ensuring data integrity through methods like message authentication codes (MACs) to guard against tampering.
- Using strong password policies and implementing robust key derivation functions (KDFs) for secure key generation.

## Disclaimer

This code is intended for educational purposes and serves as a starting point for understanding file encryption. It may not provide the level of security required for production use. Always consult security best practices and consider using established encryption libraries and frameworks for security-critical applications.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
