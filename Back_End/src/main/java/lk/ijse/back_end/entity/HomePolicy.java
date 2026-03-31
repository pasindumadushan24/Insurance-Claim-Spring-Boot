package lk.ijse.back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "home_policy")
public class HomePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String nic;
    private String email;
    private String mobile;
    private String address;

    // Home Details
    private String houseType;
    private double houseValue;
    private int yearBuilt;
    private boolean hasSecurity;

    // Policy
    private double coverageAmount;
    private int policyTerm;
    private double monthlyPremium;
    private LocalDate policyStart;
    private String plan;

    private String status; // 🔥 IMPORTANT

}
