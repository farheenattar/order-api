package models;

import com.google.gson.annotations.SerializedName;

public class PaymentDto {
	@SerializedName("order_id")
	private String orderId;
	@SerializedName("payment_mode")
	private String paymentMode;
	@SerializedName("amount")
	private String amount;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
