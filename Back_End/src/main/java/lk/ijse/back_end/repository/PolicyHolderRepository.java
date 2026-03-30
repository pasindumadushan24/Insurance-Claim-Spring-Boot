package lk.ijse.back_end.repository;


import lk.ijse.back_end.entity.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer> {
}