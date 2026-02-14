package lk.ijse.backend.DTO;



import lombok.*;

import java.util.List;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Data
    public class OrderDTO {
        private String oid;
        private Long cid;
        private String date;
        private Double total;
        private List<OrderItemDTO> items;
    }

