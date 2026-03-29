package lk.ijse.back_end.service.custom;


import lk.ijse.back_end.dto.AuthDTO;
import lk.ijse.back_end.dto.AuthResponseDTO;
import lk.ijse.back_end.dto.RegisterDTO;

public interface AuthService {
    AuthResponseDTO authenticate(AuthDTO authDTO);
    String register(RegisterDTO registerDTO);
}
