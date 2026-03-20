package lk.ijse.back_end.controller;


import lk.ijse.back_end.entity.User;
import lk.ijse.back_end.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Frontend eken fetch karanna
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

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
}