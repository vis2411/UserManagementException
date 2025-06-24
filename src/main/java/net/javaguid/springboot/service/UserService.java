package net.javaguid.springboot.service;

import net.javaguid.springboot.dto.UserDto;
import net.javaguid.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createuser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUser();
    UserDto updateUser(UserDto user);
    void deleteUser(Long id);
}
