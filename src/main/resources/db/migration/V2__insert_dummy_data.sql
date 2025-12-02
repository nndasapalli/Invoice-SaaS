-- ==========================================
-- USERS (SaaS application users)
-- ==========================================
INSERT INTO users (user_name, user_email, user_password, company_name, phone, address, created_at)
VALUES
 ('Naveen Kumar', 'naveen@example.com', 'hashed_password_123', 'Naveen Tech Solutions', '9876543210', 'Hyderabad, India', NOW()),
 ('John Doe', 'john@example.com', 'hashed_password_456', 'Doe Consultancy', '9123456789', 'Bangalore, India', NOW());

-- ==========================================
-- CLIENTS (Customers of users)
-- ==========================================
INSERT INTO clients (user_id, name, email, phone, billing_address, gst_number, created_at)
VALUES
 (1, 'Acme Corporation', 'billing@acme.com', '9991122334', 'Mumbai, India', 'GST123456A', NOW()),
 (1, 'Pixel Tech', 'info@pixeltech.com', '8884455667', 'Chennai, India', 'GST789012B', NOW()),
 (2, 'Green Farms', 'contact@greenfarms.com', '7775566778', 'Delhi, India', 'GST345678C', NOW());

-- ==========================================
-- INVOICES
-- ==========================================
INSERT INTO invoices (user_id, client_id, invoice_number, invoice_date, due_date, status, sub_total, tax, total_amount, created_at)
VALUES
 (1, 1, 'INV-2024-0001', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'UNPAID', 5000, 250, 5250, NOW()),
 (1, 2, 'INV-2024-0002', NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 'PAID', 8000, 400, 8400, NOW()),
 (2, 3, 'INV-2024-0003', NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 'UNPAID', 3000, 150, 3150, NOW());

-- ==========================================
-- INVOICE ITEMS
-- ==========================================
INSERT INTO invoice_items (invoice_id, description, quantity, price, total)
VALUES
 -- invoice 1 items
 (1, 'Website Development', 1, 5000, 5000),

 -- invoice 2 items
 (2, 'Mobile App UI Design', 2, 4000, 8000),

 -- invoice 3 items
 (3, 'Logo Design', 1, 3000, 3000);

-- ==========================================
-- PAYMENTS
-- ==========================================
INSERT INTO payments (invoice_id, amount, payment_date, mode, transaction_id)
VALUES
 (2, 8400, NOW(), 'UPI', 'TXN123456789');