package com.store.pharmacy.securities.controller;

import com.store.pharmacy.securities.model.UserInput;
import com.store.pharmacy.securities.model.UserOutput;
import com.store.pharmacy.securities.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public HttpEntity<UserOutput> createUser(@Valid @RequestBody UserInput userInput) {
        userService.checkIfDuplicatedUser(userInput);
        UserOutput userOutput = userService.save(userInput);
        return new ResponseEntity<UserOutput>(userOutput, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}")
    public HttpEntity<UserOutput> findUser(@PathVariable("userId") String userId) {
        UserOutput userOutput = userService.findUser(userId);
        return new ResponseEntity<UserOutput>(userOutput, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public HttpEntity<UserOutput> updateUser(@PathVariable("userId") String userId,
                                                 @Valid @RequestBody UserInput userInput) {
        userService.checkIfUserExits(userId);
        UserOutput userOutput = userService.update(userId, userInput);
        return new ResponseEntity<UserOutput>(userOutput, HttpStatus.OK);
    }
}
