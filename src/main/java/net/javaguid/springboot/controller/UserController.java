package net.javaguid.springboot.controller;

import net.javaguid.springboot.dto.UserDto;
import net.javaguid.springboot.entity.User;
import net.javaguid.springboot.exception.ErrorDetails;
import net.javaguid.springboot.exception.ResourceNotFoundException;
import net.javaguid.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto saveUser = userService.createuser(user);
        return new ResponseEntity(saveUser,HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto getuser = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getuser);
    }


    @GetMapping("getAll")
    public ResponseEntity<List<UserDto>> getAllUser(){
       List<UserDto> users = userService.getAllUser();
       return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user){
        user.setId(userId);
        UserDto  updareUser = userService.updateUser(user);
        return new ResponseEntity<>(updareUser, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id ){
        userService.deleteUser(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }


//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }


}
