package com.invoiceGeneration.util;

import java.time.Year;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invoiceGeneration.entity.InvoiceSequence;
import com.invoiceGeneration.repository.InvoiceSequenceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceNumberGeneratorService {

    private final InvoiceSequenceRepository invoiceSequenceRepository;

    /**
     * Generate the next invoice number for the given tenant (user/company).
     * Thread-safe because the DB row is locked with PESSIMISTIC_WRITE inside the transaction.
     *
     * Example output: INV-2025-0001
     */
    @Transactional
    public String generateInvoiceNumber(Long tenantId) {
        int year = Year.now().getValue();

        // Try to find the row with a PESSIMISTIC_WRITE lock.
        Optional<InvoiceSequence> maybeSeq = invoiceSequenceRepository.findByTenantIdAndYearForUpdate(tenantId, year);

        int nextSeq;
        InvoiceSequence seqEntity;

        if (maybeSeq.isPresent()) {
            seqEntity = maybeSeq.get();
            nextSeq = seqEntity.getLastSequence() + 1;
            seqEntity.setLastSequence(nextSeq);
            invoiceSequenceRepository.save(seqEntity); // persist updated sequence
        } else {
            // No sequence row for this tenant+year -> create it (first invoice this year for tenant)
            nextSeq = 1;
            seqEntity = new InvoiceSequence();
            seqEntity.setTenantId(tenantId);
            seqEntity.setYear(year);
            seqEntity.setLastSequence(nextSeq);
            invoiceSequenceRepository.save(seqEntity);
        }

        return String.format("INV-%d-%04d", year, nextSeq);
    }
}