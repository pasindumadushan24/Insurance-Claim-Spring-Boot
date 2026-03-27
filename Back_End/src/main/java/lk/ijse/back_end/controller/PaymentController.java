package lk.ijse.back_end.controller;

import lk.ijse.back_end.service.ClaimServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ClaimServiceImpl claimService;

    @PostMapping("/notify")
    public ResponseEntity<String> notifyPayment(@RequestParam Map<String, String> params) {
        // Print all params for debugging
        params.forEach((k,v) -> System.out.println(k + " : " + v));

        String orderId = params.get("order_id");
        String statusCode = params.get("status_code");
        String receivedHash = params.get("md5sig");

        // Validate hash
        if (!validateHash(params, receivedHash)) {
            return ResponseEntity.badRequest().body("Invalid hash");
        }

        if ("2".equals(statusCode)) {
            // Payment success
            claimService.markAsPaid(orderId);
            System.out.println("✅ Payment Success: " + orderId);
        } else {
            System.out.println("❌ Payment Failed: " + orderId);
        }

        return ResponseEntity.ok("OK");
    }

    private boolean validateHash(Map<String, String> params, String receivedHash) {
        try {
            String merchantId = params.get("merchant_id");
            String orderId = params.get("order_id");
            String amount = params.get("payhere_amount");
            String currency = params.get("payhere_currency");
            String statusCode = params.get("status_code");

            String merchantSecret = "YOUR_SANDBOX_SECRET"; // Change to your PayHere sandbox secret

            String toHash = merchantId + orderId + amount + currency + statusCode + merchantSecret;

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toHash.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString().equalsIgnoreCase(receivedHash);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}