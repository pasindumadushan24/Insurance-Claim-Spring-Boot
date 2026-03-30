package lk.ijse.back_end.dto;


import lombok.Data;

@Data
public class PolicyRequestDTO {
    // PolicyHolder info
    private String nic;
    private String title;
    private String fullName;
    private String email;
    private String mobile;
    private String address1;
    private String address2;
    private String holderType;

    // Vehicle policy info
    private String vehicleType;
    private String vehicleMake;
    private String vehicleModel;
    private String fuelType;
    private String purpose;
    private String regNumber;
    private String engineCapacity;
    private String chassisNumber;
    private Integer manufactureYear;
    private String policyStart; // yyyy-MM-dd
    private String plan;
}