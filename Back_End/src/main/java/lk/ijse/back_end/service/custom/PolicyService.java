package lk.ijse.back_end.service.custom;


import lk.ijse.back_end.dto.PolicyRequestDTO;
import lk.ijse.back_end.entity.VehiclePolicy;

import java.util.List;

public interface PolicyService {
    VehiclePolicy savePolicy(PolicyRequestDTO dto);
    List<VehiclePolicy> getAllPolicies(); // 🔹 fetch all
}