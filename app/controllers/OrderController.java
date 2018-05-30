package controllers;

import models.ProductDto;
import models.ProductEntity;
import models.ProductListDto;
import play.mvc.Controller;
import service.ProductService;
import service.ProductServiceImpl;

import java.util.List;

import com.google.gson.Gson;
public class OrderController extends Controller
{
	public static void placeOrder()
	{
		String body=params.get("body");
		ProductDto productdto=new Gson().fromJson(body, ProductDto.class);
		ProductService service=new ProductServiceImpl();
		service.createOrder(productdto);
		renderJSON("{\"success\":\"true\"}");	
	}
	public static void refundOrder()
	{
		String orderId=params.get("order_id");
		ProductService service=new ProductServiceImpl();
		service.refundOrderImpl(orderId);
		renderJSON("{\"success\":\"true\"}");	

	}
	public static void getOrderById()
	{
		String orderId=params.get("order_id");
		ProductService service=new ProductServiceImpl();
		String jsonResponse=service.getOrder(orderId);
		renderJSON(jsonResponse);	
	}
	public static void findAllOrders()
	{
		ProductService service=new ProductServiceImpl();
		List<ProductListDto> productList=service.findOrders();
		String jsonString=new Gson().toJson(productList);
		renderJSON(jsonString);	
	}
}
