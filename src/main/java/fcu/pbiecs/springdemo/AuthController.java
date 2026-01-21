package fcu.pbiecs.springdemo;

import fcu.pbiecs.springdemo.model.LoginRequest;
import fcu.pbiecs.springdemo.model.User;
import fcu.pbiecs.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername().toUpperCase();
        String password = loginRequest.getPassword();

        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("帳號不存在");
        }

        User user = userOptional.get();

        // WARNING: Storing and comparing passwords in plain text is highly insecure.
        // In a real application, you must use a password encoder (e.g., BCryptPasswordEncoder).
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("密碼錯誤");
        }

        // Login successful, return user info (without password)
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
