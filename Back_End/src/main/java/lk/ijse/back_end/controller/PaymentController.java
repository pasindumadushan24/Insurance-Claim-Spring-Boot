package lk.ijse.back_end.controller;

import lk.ijse.back_end.service.custom.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Map;
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/card")
    public ResponseEntity<?> payByCard(@RequestBody Map<String, Object> data) {

        String claimId = data.get("claimId").toString();
        double amount = Double.parseDouble(data.get("amount").toString());

        paymentService.payByCard(claimId, amount);

        return ResponseEntity.ok("Card Payment Success");
    }

    @PostMapping("/cash")
    public ResponseEntity<?> payByCash(@RequestBody Map<String, Object> data) {

        String claimId = data.get("claimId").toString();
        double amount = Double.parseDouble(data.get("amount").toString());

        paymentService.payByCash(claimId, amount);

        return ResponseEntity.ok("Cash Payment Recorded");
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}