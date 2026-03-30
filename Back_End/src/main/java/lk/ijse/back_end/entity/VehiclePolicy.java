package lk.ijse.back_end.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle_policy")
public class VehiclePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vehicleType;
    private String vehicleMake;
    private String vehicleModel;
    private String fuelType;
    private String purpose;
    private String regNumber;
    private String engineCapacity;
    private String chassisNumber;
    private Integer manufactureYear;
    private LocalDate policyStart;
    private String plan;

    @ManyToOne
    @JoinColumn(name="policyholder_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PolicyHolder policyHolder;
}