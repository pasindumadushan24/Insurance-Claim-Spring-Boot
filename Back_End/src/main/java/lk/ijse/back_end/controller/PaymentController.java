package lk.ijse.back_end.controller;

import lk.ijse.back_end.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ClaimService claimService;

    @PostMapping("/notify")
    public ResponseEntity<String> notifyPayment(@RequestParam Map<String, String> params) {

        String orderId = params.get("order_id");
        String statusCode = params.get("status_code");
        String receivedHash = params.get("md5sig");

        System.out.println("PARAMS: " + params);

        // ✅ HASH VALIDATION
        if (!validateHash(params, receivedHash)) {
            System.out.println("❌ INVALID HASH");
            return ResponseEntity.badRequest().body("Invalid hash");
        }

        if ("2".equals(statusCode)) {
            claimService.markAsPaid(orderId);
            System.out.println("✅ Payment SUCCESS: " + orderId);
        } else {
            System.out.println("❌ Payment FAILED");
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

            String merchantSecret = "YOUR_MERCHANT_SECRET";

            String secretHash = md5(merchantSecret).toUpperCase();

            String raw = merchantId + orderId + amount + currency + statusCode + secretHash;

            String localHash = md5(raw).toUpperCase();

            System.out.println("LOCAL HASH: " + localHash);
            System.out.println("RECEIVED HASH: " + receivedHash);

            return localHash.equalsIgnoreCase(receivedHash);

        } catch (Exception e) {
            return false;
        }
    }

    private String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}