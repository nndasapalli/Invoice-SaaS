package com.invoiceGeneration.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // invoice belongs to which SaaS user

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@Column(name = "invoice_number")
	private String invoiceNumber;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "status")
	private String status; // PAID / UNPAID / OVERDUE

	private Double subTotal;
	private Double tax;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "created_at")
	private Date createdAt;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<InvoiceItem> items;
}