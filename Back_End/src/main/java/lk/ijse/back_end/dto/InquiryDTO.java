package lk.ijse.back_end.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO {
    private int id;
    private String name;
    private String email;
    private String message;
}