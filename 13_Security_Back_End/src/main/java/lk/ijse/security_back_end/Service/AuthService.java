package lk.ijse.security_back_end.Service;

import lk.ijse.security_back_end.DTO.AuthDTO;
import lk.ijse.security_back_end.DTO.AuthResponseDTO;
import lk.ijse.security_back_end.Repository.UserRepository;
import lk.ijse.security_back_end.Utill.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
            private final ;
    public AuthResponseDTO authenticate(AuthDTO){
        userRepository.findByUsername(authDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(authDTO.getUsername())
        )
    }
}
