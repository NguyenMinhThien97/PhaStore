package com.store.pharmacy.securities.service;

import com.store.pharmacy.securities.model.UserInput;
import com.store.pharmacy.securities.model.UserOutput;

public interface UserService {

    UserOutput save(UserInput userInput);

    UserOutput findUser(String userId);

    UserOutput update(String userId, UserInput UserInput);

    void checkIfDuplicatedUser(UserInput userInput);

    void checkIfUserExits(String userId);

}
