package lk.ijse.backend.DTO;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long oid;
    private String date;
    private Long cid;

    private List<OrderDetailDTO> orderDetails;
}