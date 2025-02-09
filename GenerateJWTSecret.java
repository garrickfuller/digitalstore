import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

public class GenerateJwtSecret {
    public static void main(String[] args) {
        // Generate a random 256-bit key suitable for HS256
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // Encode it in Base64
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Your JWT Secret (Base64): " + base64Key);
    }
}