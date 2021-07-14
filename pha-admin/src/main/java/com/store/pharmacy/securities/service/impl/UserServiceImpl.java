package com.store.pharmacy.securities.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.store.pharmacy.common.model.Common;
import com.store.pharmacy.common.repository.CommonRepository;
import com.store.pharmacy.exception.DataNotFoundException;
import com.store.pharmacy.exception.DuplicateDataException;
import com.store.pharmacy.securities.model.User;
import com.store.pharmacy.securities.model.UserDTO;
import com.store.pharmacy.securities.repository.UserRepository;
import com.store.pharmacy.securities.service.UserService;
import com.store.pharmacy.utils.ExecContext;
import com.store.pharmacy.utils.PharmacyConstant;
import com.store.pharmacy.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ExecContext execContext;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CommonRepository commonRepository;

	@Override
	@Transactional
	public String save(UserDTO userDTO) {
		String[] firstName = userDTO.getFirstName().split(" ");
		String userName = userRepository.generateUserName(firstName[firstName.length - 1].charAt(0),
				userDTO.getLastName());
		userDTO.setUserName(userName);
		LocalDate dateOfBirth = userDTO.getDateOfBirth();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateOfBirth.getYear());
		stringBuilder.append(StringUtils.leftPad(String.valueOf(dateOfBirth.getMonthValue()), 3, "0"));
		stringBuilder.append(StringUtils.leftPad(String.valueOf(dateOfBirth.getDayOfMonth()), 2, "0"));
		String password = passwordEncoder.encode(stringBuilder.toString());
		String userId = userRepository.generateUserId();
		Common common = commonRepository.findByCommonCodeAndName(PharmacyConstant.ROLE_USER,
				userDTO.getRoleName().trim());
		if (common == null) {
			throw new DataNotFoundException("MSG0016", new Object[] { userDTO.getRoleName().trim() });
		}
		User user = mapUserDTOToUser(userDTO, userId, common.getSequenceNo(), userName.trim(), password);
		userRepository.saveAndFlush(user);
		return userId;
	}

	@Override
	public UserDTO findUser(String userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new DataNotFoundException("MSG0011", new Object[] { userId });
		}
		UserDTO userDTO = mapUserToUserDTO(user);
		return userDTO;
	}

	private UserDTO mapUserToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEnabled(user.isEnabled());
		userDTO.setUserName(user.getUserName());
		userDTO.setLastName(user.getLastName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
			userDTO.setEmail(user.getEmail());
		}
		if (user.getAddress() != null && !user.getAddress().isEmpty()) {
			userDTO.setAddress(user.getAddress());
		}
		Common common = commonRepository.findByCommonCodeAndSequenceNo(PharmacyConstant.ROLE_USER,
				user.getSequenceNo());
		if (common == null) {
			throw new DataNotFoundException("MSG0019", new Object[] { user.getSequenceNo() });
		}
		userDTO.setRoleName(common.getName());
		return userDTO;
	}

	private User mapUserDTOToUser(UserDTO userDTO, String userId, int sequenceNo, String userName, String password) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setSequenceNo(sequenceNo);
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setLastName(userDTO.getLastName());
		user.setFirstName(userDTO.getFirstName());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setCreateAt(LocalDateTime.now(ZoneId.of(Utils.getTimeZone())));
		if (execContext.getUserId() != null && !execContext.getUserId().isEmpty()) {
			user.setCreateBy(execContext.getUserId());
		}
		if (userDTO.getEnabled() != null && !userDTO.getEnabled()) {
			user.setEnabled(userDTO.getEnabled());
		} else {
			user.setEnabled(true);
			userDTO.setEnabled(true);
		}
		return user;
	}

	@Override
	public void checkIfDuplicatedUserName(String userName) throws DuplicateDataException {
		if (userName != null && userRepository.findUserByUserName(userName) != null) {
			throw new DuplicateDataException("MSG0010", new Object[] { userName });
		}
	}

	@Override
	public void checkIfDuplicatedUserEmail(String email) throws DuplicateDataException {
		if (email != null && userRepository.findUserByEmail(email) != null) {
			throw new DuplicateDataException("MSG0008", new Object[] { email });
		}
	}

	@Override
	public void checkIfDuplicatedUser(UserDTO userDTO) {
		checkIfDuplicatedUserEmail(userDTO.getEmail());
		checkIfDuplicatedUserName(userDTO.getUserName());
	}
}
