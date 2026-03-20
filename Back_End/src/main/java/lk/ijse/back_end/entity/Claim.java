package lk.ijse.back_end.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Claim {

    @Id
    private String claimId;

    private double amount;

    private String paymentStatus;
}