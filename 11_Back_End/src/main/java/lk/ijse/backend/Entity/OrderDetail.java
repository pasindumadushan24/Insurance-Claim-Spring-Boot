package lk.ijse.backend.Entity;

import jakarta.persistence.*;
import lombok.*;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class OrderDetail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Orders orders;
//
//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    private Item item;
//
//    private int qty;
//    private double unitPrice;
//}



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "oid")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "iid")
    private Item item;

    private int qty;
    private double unitPrice;
}