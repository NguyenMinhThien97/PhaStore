package com.store.pharmacy.shop.repository;

import com.store.pharmacy.shop.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopUserRepository  extends JpaRepository<ShopUser, Long> {
}
