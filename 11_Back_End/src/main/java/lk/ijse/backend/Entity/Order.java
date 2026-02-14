package lk.ijse.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String oid;

    private String date;
    private double total;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customer;   // 🔥 meka add karanna
}
