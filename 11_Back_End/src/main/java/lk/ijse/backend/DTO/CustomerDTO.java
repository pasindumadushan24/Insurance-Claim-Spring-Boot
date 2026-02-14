package lk.ijse.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDTO {
//    @Pattern(regexp = "^\\d+$", message = "Customer ID must be a valid integer")
    private Long cid;

    @NotBlank(message = "Customer name is mandatory")
    private String name;

    @NotBlank(message = "Customer address is mandatory")
    @Size(min = 10, message = "Address must be at least 5 characters long")
    private String address;

}
