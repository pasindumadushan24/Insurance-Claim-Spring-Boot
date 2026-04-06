package lk.ijse.back_end.dto;

import lombok.Data;

@Data
public class LifePolicyDTO {

    private Integer id; // numeric database ID, buttons use karanna
    private String policyCode;
    private String fullName;
    private String nic;
    private String gender;
    private String dob;
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
    private String policyStart;
    private String plan;
    private String status;

}