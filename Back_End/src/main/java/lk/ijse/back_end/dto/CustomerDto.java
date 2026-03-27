package lk.ijse.back_end.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String address;
    private String contactNo;
    private String insuranceType;
    private String policyNumber;
}
