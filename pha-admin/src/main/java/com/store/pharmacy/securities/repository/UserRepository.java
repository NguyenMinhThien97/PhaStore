package com.store.pharmacy.securities.repository;

import com.store.pharmacy.securities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUserId(String userId);

    User findUserByUserName(String userName);

    User findUserByEmail(String email);

    @Query(value = "SELECT generateUserId()", nativeQuery = true)
    String generateUserId();

    @Query(value = "SELECT generateUserName(:firstName, :lastName)", nativeQuery = true)
    String generateUserName(@Param("firstName") Character firstName, @Param("lastName") String lastName);
}
