package com.store.pharmacy.client.repository;

import com.store.pharmacy.client.model.ClientCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCompanyRepository extends JpaRepository<ClientCompany, Long> {
}
