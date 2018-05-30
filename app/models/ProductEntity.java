package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.db.jpa.Model;

import com.google.gson.annotations.SerializedName;

@Entity
@Table(name="orderInvoice")
public class ProductEntity extends Model
{
	@Column(name="product_name")
	private String productName;
	@Column(name="product_price")
	private String productPrice;
	@Column(name="status")
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
