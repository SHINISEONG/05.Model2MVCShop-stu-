package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-aspect.xml",
		"classpath:config/context-common.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class ProductServiceTest {
	///field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		
		
		product.setProdName("s22");
		product.setProdDetail("최적화그지같은폰");
		product.setPrice(200000);
		product.setManuDate("20220202");
		product.setFileName("galaxyImage.jpg");
		product.setStock(3);
		
		productService.addProduct(product);
		
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		Product product = new Product();
			
		product = productService.getProduct(10008);
		
		Assert.assertEquals(10008, product.getProdNo());
		Assert.assertEquals("s22", product.getProdName());
		Assert.assertEquals("최적화그지같은폰", product.getProdDetail());
		Assert.assertEquals(200000, product.getPrice());
		Assert.assertEquals("20220202", product.getManuDate());
		Assert.assertEquals("galaxyImage.jpg", product.getFileName());
		Assert.assertEquals(3, product.getStock());
		
	}

	//@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setProdNo(10008);
		product.setProdName("에스이이");
		product.setProdDetail("최적화그지같은폰");
		product.setPrice(200000);
		product.setManuDate("20220202");
		product.setFileName("galaxyImage.jpg");
		product.setStock(4);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10008);
		
		Assert.assertEquals("에스이이", product.getProdName());
		Assert.assertEquals(4, product.getStock());
		
	}
	
	//@Test
	public void testGetProductListAll() throws Exception {
		Search search = new Search();
	 	search.setCurrentPage(2);
	 	search.setPageSize(3);
	 	search.setSearchOrderType("orderByDateDESC");
	 	search.setSearchMinPrice(0);
	 	search.setSearchMaxPrice(0);
	 	
	 	Map<String,Object> map = productService.getProductList(search);
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	Assert.assertEquals(13, map.get("totalCount"));
	}
	
	//@Test
	public void testGetProductListByProductName() throws Exception {
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchOrderType("orderByDateDESC");
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("보");
	 	search.setSearchMinPrice(0);
	 	search.setSearchMaxPrice(0);
	 	
	 	Map<String,Object> map = productService.getProductList(search);
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	Assert.assertEquals(2, map.get("totalCount"));
	 	

	}
}
