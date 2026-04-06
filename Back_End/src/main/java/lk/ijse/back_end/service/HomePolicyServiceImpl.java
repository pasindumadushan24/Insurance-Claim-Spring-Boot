package lk.ijse.back_end.service;

import lk.ijse.back_end.dto.HomePolicyDTO;
import lk.ijse.back_end.entity.HomePolicy;
import lk.ijse.back_end.repository.HomePolicyRepository;
import lk.ijse.back_end.service.custom.HomePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePolicyServiceImpl implements HomePolicyService {

    private final HomePolicyRepository repo;

    @Override
    public String save(HomePolicyDTO dto) {

        HomePolicy p = new HomePolicy();

        p.setFullName(dto.getFullName());
        p.setNic(dto.getNic());
        p.setEmail(dto.getEmail());
        p.setMobile(dto.getMobile());
        p.setAddress(dto.getAddress());

        p.setHouseType(dto.getHouseType());
        p.setHouseValue(dto.getHouseValue());
        p.setYearBuilt(dto.getYearBuilt());
        p.setHasSecurity(dto.isHasSecurity());

        p.setCoverageAmount(dto.getCoverageAmount());
        p.setPolicyTerm(dto.getPolicyTerm());
        p.setMonthlyPremium(dto.getMonthlyPremium());
        p.setPolicyStart(LocalDate.parse(dto.getPolicyStart()));
        p.setPlan(dto.getPlan());

        p.setStatus("PENDING"); // default

        HomePolicy saved = repo.save(p); // save first to get auto-generated ID

        // Generate formatted code
        String policyCode = String.format("POLH%03d", saved.getId());

        return policyCode; // return formatted code
    }

    @Override
    public void updateStatus(Integer id, String status) {
        HomePolicy policy = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setStatus(status);
        repo.save(policy);
    }

    @Override
    public List<HomePolicy> getAll() {
        return List.of();
    }


    @Override
    public Integer getLastPolicyId() {
        return repo.findTopByOrderByIdDesc()
                .map(HomePolicy::getId)
                .orElse(null);
    }


    @Override
    public List<HomePolicyDTO> getAllPolicies() {
        return repo.findAll().stream().map(p -> {
            HomePolicyDTO dto = new HomePolicyDTO();
            dto.setId(p.getId());
            dto.setPolicyCode(String.format("POLH%03d", p.getId())); // POLH001
            dto.setFullName(p.getFullName());
            dto.setHouseType(p.getHouseType());
            dto.setHouseValue(p.getHouseValue());
            dto.setPlan(p.getPlan());
            dto.setMonthlyPremium(p.getMonthlyPremium());
            dto.setStatus(p.getStatus() != null ? p.getStatus() : "PENDING");
            return dto;
        }).toList();
    }
}