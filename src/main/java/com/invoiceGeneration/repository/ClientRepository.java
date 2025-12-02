package com.invoiceGeneration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invoiceGeneration.entity.Client;
import com.invoiceGeneration.entity.User;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByUser(User user);
}