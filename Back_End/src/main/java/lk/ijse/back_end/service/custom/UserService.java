package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User getUser(Long id);
}