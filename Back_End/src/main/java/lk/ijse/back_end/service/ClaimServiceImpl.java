package lk.ijse.back_end.service;


import lk.ijse.back_end.dto.ClaimDTO;
import lk.ijse.back_end.entity.Claim;
import lk.ijse.back_end.repository.ClaimRepository;

import lk.ijse.back_end.service.custom.ClaimService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final ModelMapper mapper;


    @Override
    public ClaimDTO saveClaim(ClaimDTO dto) {
        Claim claim = mapper.map(dto, Claim.class);

        if (claim.getClaimId() == null) {
            claim.setClaimId("C" + System.currentTimeMillis());
        }
        if (claim.getPaymentStatus() == null) {
            claim.setPaymentStatus("PENDING");
        }
        if (claim.getAmount() == 0) {
            claim.setAmount(0);
        }

        Claim saved = claimRepository.save(claim);
        return mapper.map(saved, ClaimDTO.class);
    }

    @Override
    public List<ClaimDTO> getAllClaims() {
        return claimRepository.findAll()
                .stream()
                .map(claim -> mapper.map(claim, ClaimDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClaimDTO getById(String claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        return mapper.map(claim, ClaimDTO.class);
    }

    @Override
    public void updateStatus(String claimId, String status) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setPaymentStatus(status);
        claimRepository.save(claim);
    }

    @Override
    public void markAsPaid(String claimId) {
        Claim claim = claimRepository.findById(claimId).orElse(null);
        if (claim != null) {
            claim.setPaymentStatus("APPROVED");
            claimRepository.save(claim);
        }
    }
}