package leica.blog.api.service;

import leica.blog.api.entity.user.User;
import leica.blog.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId){
        return userRepository.findByUserId(userId);
    }
}
