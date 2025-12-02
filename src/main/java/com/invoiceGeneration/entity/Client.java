package com.invoiceGeneration.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // owner of client

	private String name;
	private String email;
	private String phone;

	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "gst_number")
	private String gstNumber;

	@Column(name = "created_at")
	private Date createdAt;
}