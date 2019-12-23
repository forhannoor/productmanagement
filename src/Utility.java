// Handles some utility methods, i.e. ID generation.

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Utility {
	private static MessageDigest messageDigest;
	
	// Given a product, generates its ID using SHA-256.
	public static byte[] generateId(Product product) {
		if(messageDigest == null) {
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return messageDigest.digest(product.toString().getBytes(StandardCharsets.UTF_8));
	}
}