package lk.ijse.back_end.repository;


import lk.ijse.back_end.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

}