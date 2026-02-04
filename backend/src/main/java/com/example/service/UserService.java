package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class UserService {

    // 模拟数据库存储
    private static final List<User> users = new ArrayList<>();

    static {
        // 初始化一些测试数据
        User user1 = new User();
        user1.setId(1L);
        user1.setPhone("13800138001");
        user1.setUserId("user1");
        user1.setRole("admin");
        user1.setVip(true);
        user1.setCrmId("crm1");
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());
        users.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setPhone("13800138002");
        user2.setUserId("user2");
        user2.setRole("user");
        user2.setVip(false);
        user2.setCrmId("crm2");
        user2.setCreatedAt(LocalDateTime.now());
        user2.setUpdatedAt(LocalDateTime.now());
        users.add(user2);

        User user3 = new User();
        user3.setId(3L);
        user3.setPhone("13800138003");
        user3.setUserId("user3");
        user3.setRole("vip");
        user3.setVip(true);
        user3.setCrmId("crm3");
        user3.setCreatedAt(LocalDateTime.now());
        user3.setUpdatedAt(LocalDateTime.now());
        users.add(user3);
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
     * 根据手机号获取用户
     * @param phone 手机号
     * @return 用户对象
     */
    public Optional<User> getUserByPhone(String phone) {
        return users.stream()
                .filter(user -> user.getPhone().equals(phone))
                .findFirst();
    }

    /**
     * 根据用户ID获取用户
     * @param userId 用户ID
     * @return 用户对象
     */
    public Optional<User> getUserByUserId(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
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
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
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
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setUserId(user.getUserId());
            userToUpdate.setRole(user.getRole());
            userToUpdate.setVip(user.isVip());
            userToUpdate.setCrmId(user.getCrmId());
            userToUpdate.setUpdatedAt(LocalDateTime.now());
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
