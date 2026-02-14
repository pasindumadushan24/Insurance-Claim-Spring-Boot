package lk.ijse.backend.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {


    private Long oid;
    private String iid;
    private Integer cid;
    private int qty;
    private double price;
}
