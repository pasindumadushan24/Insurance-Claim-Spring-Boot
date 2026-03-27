package lk.ijse.back_end.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance2DTO {
    private int id;
    private String insuranceType;
    private String category;
    private String vehicleNo;
    private String ownerName;
    private String email;
    private double amount;
    private String status;
}