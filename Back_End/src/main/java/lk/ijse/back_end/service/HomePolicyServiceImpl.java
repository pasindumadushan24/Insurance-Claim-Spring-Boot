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
    public HomePolicy save(HomePolicyDTO dto) {

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

        p.setStatus("PENDING"); // 🔥 default

        return repo.save(p);
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
        return repo.findAll();
    }
}