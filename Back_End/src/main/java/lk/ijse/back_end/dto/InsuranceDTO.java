package lk.ijse.back_end.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {

    private Long id;
    private String custName;
    private String custContact;
    private String policyNo;
    private String insuranceType;
    private double premium;
    private String startDate;
    private String endDate;
}