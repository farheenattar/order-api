package models;

import com.google.gson.annotations.SerializedName;

public class ProductDto {
	@SerializedName("product_name")
	private String productName;
	
	@SerializedName("product_price")
	private String productPrice;
	
	@SerializedName("payment_mode")
	private String paymentMode;
	@SerializedName("status")
	private String status;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
}
