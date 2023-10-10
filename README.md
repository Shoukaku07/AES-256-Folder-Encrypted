# AES-256-Folder-Encrypted
The `FolderEncryptor` class in the provided code is a Java program for encrypting and decrypting files within a folder using the AES (Advanced Encryption Standard) algorithm with a specified password. Here's how it works:

1. **Encryption Process:**
   - The `encryptFolder` method is called with a folder to encrypt and a password.
   - It recursively traverses the folder structure.
   - For each file encountered, it reads the file's content into `fileBytes`.
   - The `encrypt` method is called with `fileBytes` and the password.
   - Inside the `encrypt` method, a secret key is generated from the password.
   - The AES encryption algorithm is initialized with this secret key.
   - The file content (`fileBytes`) is encrypted using AES encryption, resulting in `encryptedBytes`.
   - The encrypted content is written to a new file with the extension `.hidden`.
   - The original file is deleted.

2. **Decryption Process:**
   - The `decryptFolder` method is called with a folder to decrypt and the password.
   - It recursively traverses the folder structure.
   - For each `.hidden` file encountered, it reads its content into `fileBytes`.
   - The `decrypt` method is called with `fileBytes` and the password.
   - Inside the `decrypt` method, the secret key is generated from the password.
   - The AES decryption algorithm is initialized with this secret key.
   - The file content (`fileBytes`) is decrypted using AES decryption, resulting in `decryptedBytes`.
   - The decrypted content is written to a new file with the `.hidden` extension removed.
   - The original `.hidden` file is deleted.

3. **Key Generation:**
   - The `generateKey` method creates a 128-bit (16-byte) secret key from the provided password. It does this by converting the password to bytes and truncating or padding it to 16 bytes.

4. **Encryption Algorithm Details:**
   - AES encryption is performed in ECB mode with PKCS5 padding (`"AES/ECB/PKCS5Padding"`).

It's important to note that while this code demonstrates basic file encryption and decryption, there are important security considerations to keep in mind when implementing encryption in a real-world application. For example, securely storing and managing encryption keys, handling exceptions, and ensuring data integrity. Additionally, AES-256 encryption is considered more secure than the 128-bit key used in this code.

## How to use it?

You can download the java file that I have provided. then place them in the respective java src, making sure to change the package according to your application.

How to start encrypted?
```java
FolderEncryptor.encryptFolder("/storage/emulated/0/Download", "MyPassword");
```
Decrypted:
```java
FolderEncryptor.decryptFolder("/storage/emulated/0/Download", "MyPassword");
```

How to encrypt single file?
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
