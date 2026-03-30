package lk.ijse.back_end.repository;


import lk.ijse.back_end.entity.VehiclePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclePolicyRepository extends JpaRepository<VehiclePolicy, Integer> {
}