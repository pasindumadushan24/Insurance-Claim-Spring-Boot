package lk.ijse.backend.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private String itemId;
    private int qty;
    private double unitPrice;
}