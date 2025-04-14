package junitquickcheck.crypto;

public class EncryptionResult {
        final byte[] ciphertext;
        final byte[] iv;

        EncryptionResult(byte[] ciphertext, byte[] iv) {
            this.ciphertext = ciphertext;
            this.iv = iv;
        }
}
