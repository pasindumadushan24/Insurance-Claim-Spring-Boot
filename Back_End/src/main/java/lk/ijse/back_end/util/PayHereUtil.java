package lk.ijse.back_end.util;


import java.math.BigDecimal;
import java.security.MessageDigest;

public class PayHereUtil {

    public static String generateHash(String merchantId, String orderId,
                                      BigDecimal amount, String currency,
                                      String merchantSecret) {

        try {
            String formattedAmount = String.format("%.2f", amount);

            String secretHash = md5(merchantSecret).toUpperCase();

            String raw = merchantId + orderId + formattedAmount + currency + secretHash;

            return md5(raw).toUpperCase();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
