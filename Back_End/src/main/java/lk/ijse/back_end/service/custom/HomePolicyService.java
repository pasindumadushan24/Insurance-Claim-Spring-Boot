package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.HomePolicyDTO;
import lk.ijse.back_end.entity.HomePolicy;

import java.util.List;

public interface HomePolicyService {

    // Return formatted Policy code
    String save(HomePolicyDTO dto);

    void updateStatus(Integer id, String status);

    List<HomePolicy> getAll();
    Integer getLastPolicyId();

    List<HomePolicyDTO> getAllPolicies();
}