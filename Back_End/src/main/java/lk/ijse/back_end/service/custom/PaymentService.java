package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.entity.Payment;

import java.util.List;

public interface PaymentService {
    void payByCard(String claimId, double amount);
    void payByCash(String claimId, double amount);

    List<Payment> getAllPayments();
}