package lk.ijse.back_end.service;

import lk.ijse.back_end.dto.LifePolicyDTO;
import lk.ijse.back_end.entity.LifePolicy;
import lk.ijse.back_end.repository.LifePolicyRepository;
import lk.ijse.back_end.service.custom.LifePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;@Service
@RequiredArgsConstructor
public class LifePolicyServiceImpl implements LifePolicyService {

    private final LifePolicyRepository repo;

    @Override
    public String save(LifePolicyDTO dto) {

        LifePolicy p = new LifePolicy();

        // 🔹 Personal
        p.setFullName(dto.getFullName());
        p.setNic(dto.getNic());
        p.setGender(dto.getGender());
        p.setDob(LocalDate.parse(dto.getDob()));
        p.setEmail(dto.getEmail());
        p.setMobile(dto.getMobile());
        p.setAddress(dto.getAddress());

        // 🔹 Health
        p.setHeight(dto.getHeight());
        p.setWeight(dto.getWeight());
        p.setSmoker(dto.isSmoker());
        p.setDiseases(dto.getDiseases());

        // 🔹 Policy
        p.setSumAssured(dto.getSumAssured());
        p.setPolicyTerm(dto.getPolicyTerm());
        p.setMonthlyPremium(dto.getMonthlyPremium());
        p.setPolicyStart(LocalDate.parse(dto.getPolicyStart()));
        p.setPlan(dto.getPlan());

        p.setStatus("PENDING");

        LifePolicy saved = repo.save(p);

        return String.format("POLL%03d", saved.getId());
    }

    @Override
    public void updateStatus(Integer id, String status) {
        LifePolicy policy = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setStatus(status);
        repo.save(policy);
    }

    @Override
    public List<LifePolicy> getAll() {
        return List.of();
    }

    @Override
    public Integer getLastPolicyId() {
        return repo.findTopByOrderByIdDesc()
                .map(LifePolicy::getId)
                .orElse(null);
    }

    @Override
    public List<LifePolicyDTO> getAllPolicies() {
        return repo.findAll().stream().map(p -> {
            LifePolicyDTO dto = new LifePolicyDTO();

            dto.setId(p.getId());
            dto.setPolicyCode("POLL" + String.format("%03d", p.getId()));
            dto.setFullName(p.getFullName());
            dto.setGender(p.getGender());
            dto.setPlan(p.getPlan());
            dto.setMonthlyPremium(p.getMonthlyPremium());
            dto.setStatus(p.getStatus());

            dto.setSumAssured(p.getSumAssured());

            return dto;
        }).toList();
    }
}