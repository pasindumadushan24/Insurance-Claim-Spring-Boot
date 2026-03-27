package lk.ijse.back_end.service;


import lk.ijse.back_end.dto.InsuranceDTO;
import lk.ijse.back_end.entity.Insurance;
import lk.ijse.back_end.repository.InsuranceRepository;
import lk.ijse.back_end.service.custom.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository repo;
    private final ModelMapper modelmapper;

    @Override
    public InsuranceDTO save(InsuranceDTO dto) {

        Insurance insurance = modelmapper.map(dto, Insurance.class);

        Insurance saved = repo.save(insurance);

        return modelmapper.map(saved, InsuranceDTO.class);
    }

    @Override
    public List<InsuranceDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(i -> new InsuranceDTO(
                        i.getId(),
                        i.getCustName(),
                        i.getCustContact(),
                        i.getPolicyNo(),
                        i.getInsuranceType(),
                        i.getPremium(),
                        i.getStartDate(),
                        i.getEndDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public InsuranceDTO update(Long id, InsuranceDTO dto) {

        Insurance existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));

        existing.setCustName(dto.getCustName());
        existing.setCustContact(dto.getCustContact());
        existing.setPolicyNo(dto.getPolicyNo());
        existing.setInsuranceType(dto.getInsuranceType());
        existing.setPremium(dto.getPremium());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());

        Insurance updated = repo.save(existing);

        return new InsuranceDTO(
                updated.getId(),
                updated.getCustName(),
                updated.getCustContact(),
                updated.getPolicyNo(),
                updated.getInsuranceType(),
                updated.getPremium(),
                updated.getStartDate(),
                updated.getEndDate()
        );
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<InsuranceDTO> getByType(String type) {
        return repo.findByInsuranceType(type)
                .stream()
                .map(i -> new InsuranceDTO(
                        i.getId(),
                        i.getCustName(),
                        i.getCustContact(),
                        i.getPolicyNo(),
                        i.getInsuranceType(),
                        i.getPremium(),
                        i.getStartDate(),
                        i.getEndDate()
                ))
                .collect(Collectors.toList());
    }
}