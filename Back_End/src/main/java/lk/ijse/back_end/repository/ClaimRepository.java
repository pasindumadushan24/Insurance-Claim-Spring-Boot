package lk.ijse.back_end.repository;


import lk.ijse.back_end.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, String> {
}