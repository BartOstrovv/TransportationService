package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.dto.UserDto;
import original.transportationservicesapp.entity.User;
import original.transportationservicesapp.exception.EntityNotFoundException;
import original.transportationservicesapp.mapper.Mapper;
import original.transportationservicesapp.repository.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final Mapper mapper;

    public UserDto get(Long id) {
        return mapper.toUserDto(retrieve(id));
    }

    public List<UserDto> getAll() {
        return userRepo.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
    }

    public UserDto create(UserDto dto) {
        return mapper.toUserDto(userRepo.save(mapper.toUser(dto)));
    }

    public UserDto update(Long id, UserDto dto) {
        User user = retrieve(id);
        mapper.mergeUser(dto, user);
        return mapper.toUserDto(userRepo.save(user));
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    private User retrieve(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User", id));
    }
}
