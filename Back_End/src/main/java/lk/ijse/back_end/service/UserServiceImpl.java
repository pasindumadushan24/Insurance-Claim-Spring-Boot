package lk.ijse.back_end.service;


import lk.ijse.back_end.entity.User;
import lk.ijse.back_end.repository.UserRepository;
import lk.ijse.back_end.service.custom.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }
}