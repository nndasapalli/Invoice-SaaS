-- ===============================
-- Table: users
-- ===============================
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255),
    user_email VARCHAR(255) UNIQUE,
    user_password VARCHAR(255),
    company_name VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    created_at DATETIME
);

-- ===============================
-- Table: clients
-- ===============================
CREATE TABLE clients (
    client_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    billing_address VARCHAR(255),
    gst_number VARCHAR(50),
    created_at DATETIME,

    CONSTRAINT fk_client_user FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

-- ===============================
-- Table: invoices
-- ===============================
CREATE TABLE invoices (
    invoice_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    client_id BIGINT,

    invoice_number VARCHAR(100),
    invoice_date DATETIME,
    due_date DATETIME,
    status VARCHAR(50),

    sub_total DOUBLE,
    tax DOUBLE,
    total_amount DOUBLE,

    created_at DATETIME,

    CONSTRAINT fk_invoice_user FOREIGN KEY (user_id)
        REFERENCES users(user_id) ON DELETE CASCADE,

    CONSTRAINT fk_invoice_client FOREIGN KEY (client_id)
        REFERENCES clients(client_id) ON DELETE CASCADE
);

-- ===============================
-- Table: invoice_items
-- ===============================
CREATE TABLE invoice_items (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invoice_id BIGINT,

    description VARCHAR(255),
    quantity INT,
    price DOUBLE,
    total DOUBLE,

    CONSTRAINT fk_item_invoice FOREIGN KEY (invoice_id)
        REFERENCES invoices(invoice_id) ON DELETE CASCADE
);

-- ===============================
-- Table: payments
-- ===============================
CREATE TABLE payments (
    payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invoice_id BIGINT,

    amount DOUBLE,
    payment_date DATETIME,
    mode VARCHAR(50),
    transaction_id VARCHAR(255),

    CONSTRAINT fk_payment_invoice FOREIGN KEY (invoice_id)
        REFERENCES invoices(invoice_id) ON DELETE CASCADE
);