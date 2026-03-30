package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.LifePolicyDTO;
import lk.ijse.back_end.entity.LifePolicy;

import java.util.List;

public interface LifePolicyService {
    LifePolicy save(LifePolicyDTO dto);
    List<LifePolicy> getAll();
}