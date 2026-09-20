package vn.hoidanit.springsieutoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.model.User;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>(List.of(
                new User(1, "Hỏi Dân IT vs Eric", "hoidanit@example.com", "Hà Nội"),
                new User(2, "Nguyễn Văn A", "a.nguyen@example.com", "Hà Nội"),
                new User(3, "Trần Thị B", "b.tran@example.com", "TP.HCM"),
                new User(4, "Lê Văn C", "c.le@example.com", "Đà Nẵng")
            ));

    private int nextId = 5;

    public List<User> fetchUsers() {
        return new ArrayList<>(users);
    }

    public Optional<User> fetchUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public User createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public boolean updateUser(User updatedUser) {
        Optional<User> existingUser = fetchUserById(updatedUser.getId());
        if (existingUser.isEmpty()) {
            return false;
        }

        User user = existingUser.get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setAddress(updatedUser.getAddress());
        return true;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

}
