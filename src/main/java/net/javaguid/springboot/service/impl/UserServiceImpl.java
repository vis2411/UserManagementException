package net.javaguid.springboot.service.impl;


import net.javaguid.springboot.dto.UserDto;
import net.javaguid.springboot.entity.User;
import net.javaguid.springboot.exception.EmailAlreadyExistsException;
import net.javaguid.springboot.exception.ResourceNotFoundException;
import net.javaguid.springboot.mapper.AutoUserMapper;
import net.javaguid.springboot.mapper.UserMapper;
import net.javaguid.springboot.repository.UserRepository;
import net.javaguid.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createuser(UserDto userDto) {

        Optional<User> findEmail = userRepository.findByEmail(userDto.getEmail());
        if (findEmail.isPresent()){
            throw new EmailAlreadyExistsException("Email already Exist for the user");
        }



        // convert userDto into user JPA entity
//        User user = UserMapper.mapToUser(userDto);
//        User user = modelMapper.map(userDto,User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        //convert User JPA entity to UserDto

//        UserDto savedUserDto = UserMapper.mapToUserDto(user);
//        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long id) {
        User getUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
//        User user = getUser.get();
//        return  UserMapper.mapToUserDto(user);
//        return  modelMapper.map(user,UserDto.class);
        return  AutoUserMapper.MAPPER.mapToUserDto(getUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
//        return users.stream().map( (user)-> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return users.stream().map( (user)-> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() ->
                new ResourceNotFoundException("User","id",user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setSecondName(user.getSecondName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User checkUser = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User" , "id" , id));

        userRepository.deleteById(id);
    }


}
