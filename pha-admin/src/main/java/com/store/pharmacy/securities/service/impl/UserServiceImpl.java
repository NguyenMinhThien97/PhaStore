package com.store.pharmacy.securities.service.impl;

import com.store.pharmacy.common.model.Common;
import com.store.pharmacy.common.repository.CommonRepository;
import com.store.pharmacy.exception.DataNotFoundException;
import com.store.pharmacy.exception.DuplicateDataException;
import com.store.pharmacy.securities.model.User;
import com.store.pharmacy.securities.model.UserInput;
import com.store.pharmacy.securities.model.UserOutput;
import com.store.pharmacy.securities.repository.UserRepository;
import com.store.pharmacy.securities.service.UserService;
import com.store.pharmacy.utils.ExecContext;
import com.store.pharmacy.utils.PharmacyConstant;
import com.store.pharmacy.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ExecContext execContext;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CommonRepository commonRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserOutput save(UserInput userInput) {
        String userName = userInput.getUserName();
        if (userName == null || userName.isEmpty()) {
            String[] firstName = userInput.getFirstName().split(" ");
            userName = userRepository.generateUserName(firstName[firstName.length - 1].charAt(0),
                    userInput.getLastName());
        }
        userInput.setUserName(userName);
        LocalDate dateOfBirth = Utils.formatDateOfBirth(userInput.getDateOfBirth());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateOfBirth.getYear());
        stringBuilder.append(StringUtils.leftPad(String.valueOf(dateOfBirth.getMonthValue()), 2, "0"));
        stringBuilder.append(StringUtils.leftPad(String.valueOf(dateOfBirth.getDayOfMonth()), 2, "0"));
        String password = passwordEncoder.encode(stringBuilder.toString());
        String userId = userRepository.generateUserId();
        Common common = Optional.of(commonRepository.findByCommonCodeAndName(PharmacyConstant.ROLE_USER,
                userInput.getRoleName().trim())).orElseThrow(() -> new DataNotFoundException("MSG0016", new Object[]{userInput.getRoleName().trim()}));
        User user = convertUserInputToUser(userInput, userId, common.getSequenceNo(), userName.trim(), password);
        userRepository.saveAndFlush(user);
        return convertUserToUserOuput(user);
    }

    @Override
    public UserOutput update(String userId, UserInput userInput) {
        User user = userRepository.findById(userId).orElse(null);
        if (userInput.getRoleName() != null) {
            Common common = Optional.of(commonRepository.findByCommonCodeAndName(PharmacyConstant.ROLE_USER,
                    userInput.getRoleName().trim())).orElseThrow(() -> new DataNotFoundException("MSG0016", new Object[]{userInput.getRoleName().trim()}));
            if (user.getRoleCode() != common.getSequenceNo()) {
                user.setRoleCode(common.getSequenceNo());
            } else {
                common = Optional.of(commonRepository.findByCommonCodeAndSequenceNo(PharmacyConstant.ROLE_USER,
                        user.getRoleCode())).orElseThrow(() -> new DataNotFoundException("MSG0016", new Object[]{userInput.getRoleName().trim()}));
                userInput.setRoleName(common.getName());
            }
        }
        if (userInput.getFirstName() != null && !userInput.getFirstName().trim().isEmpty()) {
            user.setFirstName(userInput.getFirstName());
        } else {
            userInput.setFirstName(user.getFirstName());
        }
        if (userInput.getLastName() != null && !userInput.getLastName().trim().isEmpty()) {
            user.setLastName(userInput.getLastName());
        } else {
            userInput.setLastName(user.getLastName());
        }
        if (userInput.getUserName() != null && !userInput.getUserName().trim().isEmpty()) {
            user.setUserName(userInput.getUserName());
        } else {
            userInput.setUserName(user.getUserName());
        }
        if (userInput.getEmail() != null && !userInput.getEmail().trim().isEmpty()) {
            user.setEmail(userInput.getEmail());
        } else {
            userInput.setEmail(user.getEmail());
        }
        if (userInput.getDateOfBirth() != null) {
            user.setDateOfBirth(Utils.formatDateOfBirth(userInput.getDateOfBirth()));
        } else {
            userInput.setDateOfBirth(user.getDateOfBirth().toString());
        }
        if (userInput.getPhoneNumber() != null && !userInput.getPhoneNumber().trim().isEmpty()) {
            user.setPhoneNumber(userInput.getPhoneNumber());
        } else {
            userInput.setPhoneNumber(user.getPhoneNumber());
        }
        if (userInput.getAddress() != null && !userInput.getAddress().trim().isEmpty()) {
            user.setAddress(userInput.getAddress());
        } else {
            userInput.setAddress(user.getAddress());
        }
        if (userInput.getEnabled() != null) {
            user.setEnabled(userInput.getEnabled());
        } else {
            userInput.setEnabled(user.isEnabled());
        }
        if (userInput.getNewPassword() != null && userInput.getCurrentPassword() != null) {
            boolean isPasswordMatches = passwordEncoder.matches(userInput.getCurrentPassword(), user.getPassword());
            if (isPasswordMatches) {
                user.setPassword(passwordEncoder.encode(userInput.getNewPassword()));
            }
        }
        if (execContext.getUserId() != null) {
            user.setUpdatedBy(execContext.getUserId());
        }
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of(Utils.getTimeZone())));
        userRepository.save(user);
        return convertUserToUserOuput(user);
    }

    @Override
    public UserOutput findUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("MSG0011", new Object[]{userId}));
        return convertUserToUserOuput(user);
    }

    private UserOutput convertUserToUserOuput(User user) {
        Common common = Optional.of(commonRepository.findByCommonCodeAndSequenceNo(PharmacyConstant.ROLE_USER,
                user.getRoleCode())).orElseThrow(() -> new DataNotFoundException("MSG0019", new Object[]{user.getRoleCode()}));
        UserOutput userOutput = modelMapper.map(user, UserOutput.class);
        userOutput.setRoleName(common.getName());
        return userOutput;
    }

    private User convertUserInputToUser(UserInput userInput, String userId, int sequenceNo, String userName, String password) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRoleCode(sequenceNo);
        user.setEmail(userInput.getEmail());
        user.setAddress(userInput.getAddress());
        user.setLastName(userInput.getLastName());
        user.setFirstName(userInput.getFirstName());
        user.setDateOfBirth(Utils.formatDateOfBirth(userInput.getDateOfBirth()));
        user.setPhoneNumber(userInput.getPhoneNumber());
        user.setCreateAt(LocalDateTime.now(ZoneId.of(Utils.getTimeZone())));
        if (execContext.getUserId() != null && !execContext.getUserId().isEmpty()) {
            user.setCreateBy(execContext.getUserId());
        }
        if (userInput.getEnabled() != null && !userInput.getEnabled()) {
            user.setEnabled(userInput.getEnabled());
        } else {
            user.setEnabled(true);
            userInput.setEnabled(true);
        }
        return user;
    }

    @Override
    public void checkIfDuplicatedUser(UserInput userInput) {
        if (userInput.getUserName() != null && userRepository.findUserByUserName(userInput.getUserName()) != null) {
            throw new DuplicateDataException("MSG0010", new Object[]{userInput.getUserName()});
        }
        if (userInput.getEmail() != null && userRepository.findUserByEmail(userInput.getEmail()) != null) {
            throw new DuplicateDataException("MSG0008", new Object[]{userInput.getEmail()});
        }
    }

    @Override
    public void checkIfUserExits(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("MSG0011", new Object[]{userId}));
    }
}
