package lk.ijse.security_back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
    private int status;
    private String message;
    public Object data;
}
