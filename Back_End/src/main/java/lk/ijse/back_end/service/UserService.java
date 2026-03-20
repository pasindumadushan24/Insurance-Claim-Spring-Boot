package lk.ijse.back_end.service;


import lk.ijse.back_end.entity.User;
import lk.ijse.back_end.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    public User saveUser(User user){
        return repo.save(user);
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public void deleteUser(Long id){
        repo.deleteById(id);
    }

    public User getUser(Long id){
        return repo.findById(id).orElse(null);
    }
}