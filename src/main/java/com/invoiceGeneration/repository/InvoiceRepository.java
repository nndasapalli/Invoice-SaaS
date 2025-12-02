package com.invoiceGeneration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.User;
import com.invoiceGeneration.entity.Client;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUser(User user);

    List<Invoice> findByClient(Client client);

    Invoice findByInvoiceNumber(String invoiceNumber);

    Invoice findTopByOrderByInvoiceIdDesc();
}