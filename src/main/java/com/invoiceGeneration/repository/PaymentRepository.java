package com.invoiceGeneration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invoiceGeneration.entity.Payment;
import com.invoiceGeneration.entity.Invoice;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByInvoice(Invoice invoice);
}