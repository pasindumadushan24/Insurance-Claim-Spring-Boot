package lk.ijse.back_end.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String custName;
    private String custContact;
    private String policyNo;
    private String insuranceType;
    private double premium;
    private String startDate;
    private String endDate;
}