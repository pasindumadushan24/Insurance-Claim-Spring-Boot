package lk.ijse.back_end.service;

import lk.ijse.back_end.entity.Claim;
import lk.ijse.back_end.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;

    public void markAsPaid(String claimId) {
        Claim claim = claimRepository.findById(claimId).orElseThrow();
        claim.setPaymentStatus("PAID");
        claimRepository.save(claim);
    }
}