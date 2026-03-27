package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.InsuranceDTO;

import java.util.List;

public interface InsuranceViewService {

    InsuranceDTO save(InsuranceDTO dto);

    List<InsuranceDTO> getAll();

    void delete(Long id);
}