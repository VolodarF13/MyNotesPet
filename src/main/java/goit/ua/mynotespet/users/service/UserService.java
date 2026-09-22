package goit.ua.mynotespet.users.service;

import goit.ua.mynotespet.users.entity.User;
import goit.ua.mynotespet.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()) {
            return null;
        }
        return user.get();
    }

}
