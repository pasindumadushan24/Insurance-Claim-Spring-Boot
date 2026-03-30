package lk.ijse.back_end.controller;

import lk.ijse.back_end.entity.User;

import lk.ijse.back_end.repository.UserRepository;
import lk.ijse.back_end.service.custom.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository userRepository;



    @PostMapping
    public User addUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return service.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return service.getUser(id);
    }
    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication){
        return userRepository.findByUsername(authentication.getName()).orElse(null);
    }
}