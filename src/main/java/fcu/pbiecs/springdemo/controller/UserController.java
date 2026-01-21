package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.User;
import fcu.pbiecs.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer userId) {
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Convert username to uppercase for consistent storage and checking
        user.setUsername(user.getUsername().toUpperCase());

        // Check if username already exists
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(409).body("此帳號已被註冊"); // 409 Conflict
        }

        User newUser = userService.save(user);
        return ResponseEntity.status(201).body(newUser); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer userId,
                                           @RequestBody User userDetails) {
        return userService.findById(userId)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword()); // Handle password hashing appropriately
                    user.setRole(userDetails.getRole());
                    user.setStudent(userDetails.getStudent());
                    user.setTeacher(userDetails.getTeacher());
                    User updatedUser = userService.save(user);
                    return ResponseEntity.ok().body(updatedUser);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) {
        return userService.findById(userId)
                .map(user -> {
                    userService.deleteById(userId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
