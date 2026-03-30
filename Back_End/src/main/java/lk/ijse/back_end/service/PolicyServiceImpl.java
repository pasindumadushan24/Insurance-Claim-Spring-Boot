package lk.ijse.back_end.service;


import lk.ijse.back_end.dto.PolicyRequestDTO;
import lk.ijse.back_end.entity.PolicyHolder;
import lk.ijse.back_end.entity.VehiclePolicy;
import lk.ijse.back_end.repository.PolicyHolderRepository;
import lk.ijse.back_end.repository.VehiclePolicyRepository;
import lk.ijse.back_end.service.custom.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyHolderRepository holderRepo;
    private final VehiclePolicyRepository policyRepo;

    @Override
    public VehiclePolicy savePolicy(PolicyRequestDTO dto) {
        PolicyHolder holder = new PolicyHolder();
        holder.setNic(dto.getNic());
        holder.setTitle(dto.getTitle());
        holder.setFullName(dto.getFullName());
        holder.setEmail(dto.getEmail());
        holder.setMobile(dto.getMobile());
        holder.setAddress1(dto.getAddress1());
        holder.setAddress2(dto.getAddress2());
        holder.setHolderType(dto.getHolderType());
        holder = holderRepo.save(holder);

        VehiclePolicy policy = new VehiclePolicy();
        policy.setVehicleType(dto.getVehicleType());
        policy.setVehicleMake(dto.getVehicleMake());
        policy.setVehicleModel(dto.getVehicleModel());
        policy.setFuelType(dto.getFuelType());
        policy.setPurpose(dto.getPurpose());
        policy.setRegNumber(dto.getRegNumber());
        policy.setEngineCapacity(dto.getEngineCapacity());
        policy.setChassisNumber(dto.getChassisNumber());
        policy.setManufactureYear(dto.getManufactureYear());
        policy.setPolicyStart(LocalDate.parse(dto.getPolicyStart()));
        policy.setPlan(dto.getPlan());
        policy.setPolicyHolder(holder);

        return policyRepo.save(policy);
    }

    @Override
    public List<VehiclePolicy> getAllPolicies() {
        return policyRepo.findAll(); // 🔹 fetch all policies
    }
}