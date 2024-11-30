package com.Jivak.joblisting.service;

import com.Jivak.joblisting.model.User;
import com.Jivak.joblisting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {
        // Directly save the user with plain password (not recommended for production)
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        // Find the user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Directly compare the stored password with the provided password
            if (password.equals(user.getPassword())) {
                // Passwords match, return the user without the password field
                user.setPassword(null);
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
