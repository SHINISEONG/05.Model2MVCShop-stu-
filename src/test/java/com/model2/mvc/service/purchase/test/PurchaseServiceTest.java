package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-aspect.xml",
		"classpath:config/context-common.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class PurchaseServiceTest {
	///field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	public PurchaseServiceTest() {
	}
	
	@Test
	public void testAddPurchase() throws Exception {
		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();
		user.setUserId("user05");
		
		product.setProdNo(10003);
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setDivyAddr("°»±â");
		purchase.setDivyDate("2002-12-31");
		purchase.setDivyRequest("Á¶½ÉÈ÷¿À¼î");
		purchase.setPaymentOption("1");
		purchase.setQuantity(3);
		purchase.setReceiverName("µµ¶ó¿¡¸ù");
		purchase.setReceiverPhone("111-111-1111");
		purchase.setTranCode("1");
		
		purchaseService.addPurchase(purchase);

	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		Purchase purchase = null;
		purchase = purchaseService.getPerchase(10000);
		
		System.out.println(purchase.getBuyer());
		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
				
	}

	//@Test
	public void testUpdatePurchase() throws Exception {
		Purchase purchase = new Purchase();
		purchase.setTranNo(10001);
		purchase.setDivyAddr("ºÎÃµ");
		purchase.setReceiverName("¾¾´Ï½é");
		purchase.setDivyRequest(null);
		purchase.setPaymentOption("2");
		purchase.setReceiverPhone("01010");
		purchase.setDivyDate("2020-10-10");
		purchase.setQuantity(4);
		
		
		purchaseService.updatePurchase(purchase);
		
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception {
		Purchase purchase = new Purchase();
		purchase.setTranNo(10001);
		purchase.setTranCode("3");
		
		purchaseService.updateTranCode(purchase);
	}
	
	
	//@Test
	public void testGetPurchaseListAll() throws Exception{
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchOrderType("orderByDateDESC");
	 	String userId = "user01";
	 	
	 	Map<String, Object> map = purchaseService.getPurchaseList(search, userId);
	 	List<Purchase> list = (List<Purchase>)map.get("list");
	 	
	 	Assert.assertEquals(1, list.size());
	}
}

