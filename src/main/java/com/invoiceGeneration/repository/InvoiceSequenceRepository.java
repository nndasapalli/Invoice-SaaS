package com.invoiceGeneration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invoiceGeneration.entity.InvoiceSequence;

import jakarta.persistence.LockModeType;

@Repository
public interface InvoiceSequenceRepository extends JpaRepository<InvoiceSequence, Long> {

    // Finder with a DB lock to ensure exclusive access to this row
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM InvoiceSequence s WHERE s.tenantId = :tenantId AND s.year = :year")
    Optional<InvoiceSequence> findByTenantIdAndYearForUpdate(@Param("tenantId") Long tenantId,
                                                             @Param("year") Integer year);

    // Non-locking find (useful for reads)
    Optional<InvoiceSequence> findByTenantIdAndYear(Long tenantId, Integer year);
}