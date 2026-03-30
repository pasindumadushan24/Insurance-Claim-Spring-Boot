package lk.ijse.back_end.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policyholder")
public class PolicyHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nic;
    private String title;
    private String fullName;
    private String email;
    private String mobile;
    private String address1;
    private String address2;
    private String holderType;
}