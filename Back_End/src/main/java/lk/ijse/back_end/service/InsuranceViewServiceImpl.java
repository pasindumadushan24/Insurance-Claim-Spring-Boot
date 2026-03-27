package lk.ijse.back_end.service;

import lk.ijse.back_end.dto.InsuranceDTO;
import lk.ijse.back_end.entity.Insurance;
import lk.ijse.back_end.repository.InsuranceRepository;

import lk.ijse.back_end.service.custom.InsuranceViewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsuranceViewServiceImpl implements InsuranceViewService {

    private final InsuranceRepository repo;
    private final ModelMapper mapper;

    @Override
    public InsuranceDTO save(InsuranceDTO dto) {
        Insurance insurance = mapper.map(dto, Insurance.class);
        Insurance saved = repo.save(insurance);
        return mapper.map(saved, InsuranceDTO.class);
    }

    @Override
    public List<InsuranceDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(ins -> mapper.map(ins, InsuranceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}