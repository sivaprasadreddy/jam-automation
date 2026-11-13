package dev.sivalabs.jam.users;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(this::toUserDto);
    }

    @Transactional
    public void updateUser(Long userId, UpdateUserCmd cmd) {
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.updateUser(user.getId(), cmd.name());
    }

    private UserDto toUserDto(UserEntity user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
}
