package net.javaguid.springboot.mapper;

import net.javaguid.springboot.dto.UserDto;
import net.javaguid.springboot.entity.User;

public class UserMapper {

    //convert user JPA entity into userDto
    public static UserDto mapToUserDto (User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail()
        );

        return  userDto;
    }

    //convert UserDto into user JPA entity

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getEmail()
        );

        return  user;
    }



}
