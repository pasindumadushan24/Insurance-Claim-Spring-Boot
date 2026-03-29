package lk.ijse.back_end.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String role; // USER, ADMIN, OWNER
}