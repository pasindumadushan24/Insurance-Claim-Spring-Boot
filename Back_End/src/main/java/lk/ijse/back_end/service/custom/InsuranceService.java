package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.InsuranceDTO;

import java.util.List;

public interface InsuranceService {

    InsuranceDTO save(InsuranceDTO dto);

    List<InsuranceDTO> getAll();

    InsuranceDTO update(Long id, InsuranceDTO dto);

    void delete(Long id);

    List<InsuranceDTO> getByType(String type);
}