package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // 模拟数据库存储
    private static final List<User> users = new ArrayList<>();

    static {
        // 初始化一些测试数据
        users.add(new User(1L, "admin", "admin@example.com", "admin123"));
        users.add(new User(2L, "user1", "user1@example.com", "user123"));
        users.add(new User(3L, "user2", "user2@example.com", "user123"));
    }

    /**
     * 获取所有用户
     * @return 用户列表
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户对象
     */
    public Optional<User> getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加的用户对象
     */
    public User addUser(User user) {
        // 生成新的ID
        Long newId = users.stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0) + 1;
        user.setId(newId);
        users.add(user);
        return user;
    }

    /**
     * 更新用户
     * @param id 用户ID
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    public Optional<User> updateUser(Long id, User user) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            return Optional.of(userToUpdate);
        }
        return Optional.empty();
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

}
