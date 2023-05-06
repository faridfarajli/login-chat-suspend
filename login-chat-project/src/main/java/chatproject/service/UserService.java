package chatproject.service;

import chatproject.dto.RegisterDto;
import chatproject.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(RegisterDto registerDto);
    User findByUsername(String username);
    User findByEmail(String email);
}
