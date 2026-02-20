package lk.ijse.backend.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private String iid;
    private int qty;
    private double unitPrice;
}