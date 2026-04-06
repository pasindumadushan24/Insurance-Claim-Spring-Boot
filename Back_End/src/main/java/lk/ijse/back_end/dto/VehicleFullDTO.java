package lk.ijse.back_end.dto;


import lombok.Data;

@Data
public class VehicleFullDTO {

    // Holder
    private String nic;
    private String title;
    private String fullName;
    private String email;
    private String mobile;
    private String address1;
    private String address2;
    private String holderType;

    // Vehicle
    private String vehicleType;
    private String vehicleMake;
    private String vehicleModel;
    private String fuelType;
    private String purpose;
    private String regNumber;
    private String engineCapacity;
    private String chassisNumber;
    private Integer manufactureYear;

    // Policy
    private String policyStart;
    private String plan;


}