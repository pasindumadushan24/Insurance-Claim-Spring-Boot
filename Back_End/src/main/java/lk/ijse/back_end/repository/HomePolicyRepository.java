package lk.ijse.back_end.repository;

import lk.ijse.back_end.entity.HomePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomePolicyRepository extends JpaRepository<HomePolicy, Integer> {
}