package lk.ijse.back_end.service.custom;


import lk.ijse.back_end.dto.ClaimDTO;

import java.util.List;

public interface ClaimService {

    ClaimDTO saveClaim(ClaimDTO dto);

    List<ClaimDTO> getAllClaims();

    ClaimDTO getById(String claimId);

    void updateStatus(String claimId, String status);

    void markAsPaid(String claimId);
}