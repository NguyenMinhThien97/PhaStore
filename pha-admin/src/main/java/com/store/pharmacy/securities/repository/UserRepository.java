package com.store.pharmacy.securities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.pharmacy.securities.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	public User findUserByUserId(String userId);
	
	public User findUserByUserName(String userName);

	public User findUserByEmail(String email);

	@Query(value = "SELECT generateUserId()", nativeQuery = true)
	public String generateUserId();

	@Query(value = "SELECT generateUserName(:firstName, :lastName)", nativeQuery = true)
	public String generateUserName(@Param("firstName") Character firstName, @Param("lastName") String lastName);
}
