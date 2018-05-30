package service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import models.PaymentDto;
import models.ProductDto;
import models.ProductEntity;
import models.ProductListDto;
import models.core.ApplicationConstants;
import groovy.json.JsonOutput;
import models.PaymentDto;
import models.ProductDto;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.WS.WSRequest;
import play.mvc.results.RenderJson;
public class ProductServiceImpl implements ProductService
{
	private final String PAYMENT_URL="http://localhost:9101";
	@Override
	public void createOrder(ProductDto productDto) {
		ProductEntity productEntity=new ProductEntity();
		productEntity.setProductName(productDto.getProductName());
		productEntity.setProductPrice(productDto.getProductPrice());
		productEntity.setStatus(ApplicationConstants.ACTIVE);
		productEntity = productEntity.save();
		String createPaymentUrl=new StringBuilder(PAYMENT_URL).append("/payment/create").toString();
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setAmount(productEntity.getProductPrice());
		paymentDto.setOrderId(String.valueOf(productEntity.getId()));
		paymentDto.setPaymentMode(productDto.getPaymentMode());
		System.out.println(productDto.getPaymentMode());
		String paymentJson = new Gson().toJson(paymentDto);
		WSRequest request = WS.url(createPaymentUrl).setHeader("Content-Type", "application/json");
		request.body = paymentJson;
		try {
			HttpResponse httpResponse = request.post();

			System.out.println(httpResponse.getStatusText());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Override
	public void refundOrderImpl(String orderId) 
	{
		ProductEntity productEntity=ProductEntity.findById(Long.parseLong(orderId));
		productEntity.setStatus(ApplicationConstants.IN_ACTIVE);
		productEntity.save();
		String deletePaymentUrl = new StringBuilder(PAYMENT_URL).append("/payment/").append(orderId).toString();
		WSRequest request = WS.url(deletePaymentUrl).setHeader("Content-Type", "application/json");
		HttpResponse httpResponse = request.delete();
		System.out.println(httpResponse.getStatusText());
	}
	@Override
	public String getOrder(String orderId)
	{
		ProductEntity productEntity=ProductEntity.find("id=?", Long.valueOf(String.valueOf(orderId))).first();
		String jsonResponse = new Gson().toJson(productEntity);
		return jsonResponse;
	}
	@Override
	public List<ProductListDto> findOrders()
	{
		List<ProductListDto> productList=new ArrayList<ProductListDto>();
		List<ProductEntity> productEntities=ProductEntity.find("status=?", ApplicationConstants.ACTIVE).fetch();
		for(ProductEntity productEntity: productEntities)
		{
			ProductListDto product=new ProductListDto();
			product.setProductName(productEntity.getProductName());
			product.setProductPrice(productEntity.getProductPrice());
			product.setOrderId(String.valueOf(productEntity.getId()));
			productList.add(product);
		}
		return productList;
	}	
}
