package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.VehicleFullDTO;
import lk.ijse.back_end.entity.VehicleFull;

import java.util.List;

public interface VehicleFullService {

    VehicleFull save(VehicleFullDTO dto);

    List<VehicleFull> getAll();

    void delete(Integer id);
}