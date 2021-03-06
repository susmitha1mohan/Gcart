package com.rush.Gcart.dao;

import java.util.List;

import com.rush.Gcart.model.Product;



public interface IProductDAO {
	
	Product get(int pid);
	List<Product> list();
	
	boolean add(Product p);
	boolean update(Product p);
	boolean delete(Product p);
	
	//business methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int cid);
	List<Product> getLatestActiveProducts(int count);	
	

}
