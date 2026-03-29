package lk.ijse.back_end.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Claim {
    @Id
    private String claimId;
    private double amount;
    private String paymentStatus;
    private String policyNo;
    private String product;
    private String name;
    private String mobile;
    private String email;
    private String message;




    @Lob
    private byte[] image;
}