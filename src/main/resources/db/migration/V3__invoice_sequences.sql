-- invoice_sequences table stores per-tenant, per-year last sequence
CREATE TABLE invoice_sequences (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tenant_id BIGINT NOT NULL,
    year INT NOT NULL,
    last_sequence INT NOT NULL,
    version BIGINT,
    CONSTRAINT uq_tenant_year UNIQUE (tenant_id, year)
);

-- optional: index to speed lookup
CREATE INDEX idx_invoice_sequences_tenant_year ON invoice_sequences (tenant_id, year);