package br.com.fiserv.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DeliverySchedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="order_id")
	private OrderTb order;
	
	private LocalDateTime deliveryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public OrderTb getOrder() {
		return order;
	}

	public void setOrder(OrderTb order) {
		this.order = order;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
