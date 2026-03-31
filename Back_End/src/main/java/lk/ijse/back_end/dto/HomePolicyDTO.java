package lk.ijse.back_end.dto;

import lombok.Data;

@Data
public class HomePolicyDTO {

    private String fullName;
    private String nic;
    private String email;
    private String mobile;
    private String address;

    private String houseType;
    private double houseValue;
    private int yearBuilt;
    private boolean hasSecurity;

    private double coverageAmount;
    private int policyTerm;
    private double monthlyPremium;
    private String policyStart;
    private String plan;
}