package lk.ijse.back_end.repository;

import lk.ijse.back_end.entity.LifePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifePolicyRepository extends JpaRepository<LifePolicy, Integer> {
}