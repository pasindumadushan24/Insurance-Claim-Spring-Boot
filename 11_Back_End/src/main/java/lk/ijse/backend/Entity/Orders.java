package lk.ijse.backend.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Orders { // 'Order' යනු SQL keyword එකක් නිසා 'Orders' ලෙස යෙදීම සුදුසුයි
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long oid;
//    private String date;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
//
//    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
//    private List<OrderDetail> orderDetails;
//}



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "place_order") // "order" යනු SQL keyword එකක් බැවින් table name එක වෙනස් කරන්න
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String date;

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    private Customer customer;

    // cascade = CascadeType.ALL දීම අනිවාර්යයි, එවිටයි OrderDetails ද save වෙන්නේ
//    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;


}