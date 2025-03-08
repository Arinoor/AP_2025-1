package banking.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256HashStrategy implements HashStrategy {
        @Override
        public String hash(String input) {
                try {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] hashbytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
                        StringBuilder sb = new StringBuilder();
                        for (byte b : hashbytes) {
                                sb.append(String.format("%02x", b));
                        }
                        return sb.toString();
                } catch (Exception e) {
                        throw new RuntimeException("Error hashing input", e);
                }
        }
}