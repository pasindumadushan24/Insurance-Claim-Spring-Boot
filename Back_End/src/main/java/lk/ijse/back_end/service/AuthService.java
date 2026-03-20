package lk.ijse.back_end.service;


import lk.ijse.back_end.dto.AuthDTO;
import lk.ijse.back_end.dto.AuthResponseDTO;
import lk.ijse.back_end.dto.RegisterDTO;
import lk.ijse.back_end.entity.Role;
import lk.ijse.back_end.entity.User;
import lk.ijse.back_end.repository.UserRepository;
import lk.ijse.back_end.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthDTO authDTO){
        //find user from db
        User user=userRepository.findByUsername(authDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(authDTO.getUsername()));
        //match passwords (db and request pass)
        if (!passwordEncoder.matches(authDTO.getPassword(),user.getPassword())){
            throw new BadCredentialsException(authDTO.getUsername());
        }
        //generate new token
        String token=jwtUtil.generateToken(authDTO.getUsername());
        return new AuthResponseDTO(token);
    }
    public String register(RegisterDTO registerDTO){
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            throw new RuntimeException("Username is already in use");
        }
        User user=User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }
}