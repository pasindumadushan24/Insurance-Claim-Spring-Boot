package lk.ijse.back_end.repository;

import lk.ijse.back_end.entity.LifePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LifePolicyRepository extends JpaRepository<LifePolicy, Integer> {
    Optional<LifePolicy> findTopByOrderByIdDesc();
}