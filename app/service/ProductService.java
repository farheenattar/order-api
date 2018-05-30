package service;

import java.util.List;

import models.ProductDto;
import models.ProductListDto;

public interface ProductService
{
	public void createOrder(ProductDto productDto);
	public void refundOrderImpl(String orderId);
	public String getOrder(String orderId);
	public List<ProductListDto> findOrders();
}
