package br.com.fiserv.dto;

import java.time.LocalDateTime;

public class DeliveryScheduleDto {
	
	private Long empoyeeId;
	
	private Long orderId;
	
	private LocalDateTime deliveryDate;

	public Long getEmpoyeeId() {
		return empoyeeId;
	}

	public void setEmpoyeeId(Long empoyeeId) {
		this.empoyeeId = empoyeeId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
