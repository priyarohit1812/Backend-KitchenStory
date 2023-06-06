package com.kitchenstory.model;

import com.kitchenstory.enums.PaymentStatus;
import com.kitchenstory.enums.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ks_payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@NotNull(message = "Payment Type is mandatory")
	private PaymentType paymentType;

	@NotNull(message = "Payment Status is mandatory")
	private PaymentStatus paymentStatus;

	private String remark;
	
	public Payment() {
		this(0L, null, null, null);
	}

	public Payment(Long paymentId, @NotNull(message = "Payment Type is mandatory") PaymentType paymentType,
			@NotNull(message = "Payment Status is mandatory") PaymentStatus paymentStatus, String remark) {
		this.paymentId = paymentId;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.remark = remark;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentType=" + paymentType + ", paymentStatus=" + paymentStatus
				+ ", remark=" + remark + "]";
	}

}
