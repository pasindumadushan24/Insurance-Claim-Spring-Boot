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
@Table(name = "life_policy")
public class LifePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String nic;
    private String gender;
    private LocalDate dob;
    private String email;
    private String mobile;
    private String address;

    private double height;
    private double weight;
    private boolean smoker;
    private String diseases;

    private double sumAssured;
    private int policyTerm;
    private double monthlyPremium;
    private LocalDate policyStart;
    private String plan;
}