package lk.ijse.back_end.repository;


import lk.ijse.back_end.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

    List<Insurance> findByInsuranceType(String type);

}