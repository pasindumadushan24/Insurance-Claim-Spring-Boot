package lk.ijse.back_end.service;


import lk.ijse.back_end.dto.VehicleFullDTO;
import lk.ijse.back_end.entity.PolicyHolder;
import lk.ijse.back_end.entity.VehicleFull;
import lk.ijse.back_end.repository.PolicyHolderRepository;
import lk.ijse.back_end.repository.VehicleFullRepository;
import lk.ijse.back_end.service.custom.VehicleFullService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleFullServiceImpl implements VehicleFullService {

    private final PolicyHolderRepository holderRepo;
    private final VehicleFullRepository repo;

    @Override
    public VehicleFull save(VehicleFullDTO dto) {

        // save holder
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

        // save vehicle full
        VehicleFull vf = new VehicleFull();
        vf.setVehicleType(dto.getVehicleType());
        vf.setVehicleMake(dto.getVehicleMake());
        vf.setVehicleModel(dto.getVehicleModel());
        vf.setFuelType(dto.getFuelType());
        vf.setPurpose(dto.getPurpose());
        vf.setRegNumber(dto.getRegNumber());
        vf.setEngineCapacity(dto.getEngineCapacity());
        vf.setChassisNumber(dto.getChassisNumber());
        vf.setManufactureYear(dto.getManufactureYear());
        vf.setPolicyStart(LocalDate.parse(dto.getPolicyStart()));
        vf.setPlan(dto.getPlan());
        vf.setPolicyHolder(holder);

        return repo.save(vf);
    }

    @Override
    public List<VehicleFull> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}