package lk.ijse.back_end.service;

import lk.ijse.back_end.entity.Payment;
import lk.ijse.back_end.repository.PaymentRepository;
import lk.ijse.back_end.service.custom.ClaimService;
import lk.ijse.back_end.service.custom.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepo;
    private final ClaimService claimService;

    @Override
    public void payByCard(String claimId, double amount) {

        // 💡 simulate card validation
        if (amount <= 0) throw new RuntimeException("Invalid amount");

        Payment payment = new Payment();
        payment.setClaimId(claimId);
        payment.setAmount(amount);
        payment.setMethod("CARD");
        payment.setStatus("SUCCESS");

        paymentRepo.save(payment);

        // update claim
        claimService.markAsPaid(claimId);
    }

    @Override
    public void payByCash(String claimId, double amount) {

        Payment payment = new Payment();
        payment.setClaimId(claimId);
        payment.setAmount(amount);
        payment.setMethod("CASH");
        payment.setStatus("PENDING");

        paymentRepo.save(payment);
    }
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll(); // DB එකේ සියලු payment fetch කරනවා
    }

}