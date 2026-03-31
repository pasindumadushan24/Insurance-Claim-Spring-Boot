package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.HomePolicyDTO;
import lk.ijse.back_end.entity.HomePolicy;

import java.util.List;

public interface HomePolicyService {

    HomePolicy save(HomePolicyDTO dto);

    void updateStatus(Integer id, String status);

    List<HomePolicy> getAll();
}