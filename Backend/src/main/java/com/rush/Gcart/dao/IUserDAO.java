package com.rush.Gcart.dao;

import java.util.List;

import com.rush.Gcart.model.Address;
import com.rush.Gcart.model.Cart;
import com.rush.Gcart.model.User;



public interface IUserDAO {
	
	//to add a user in db
		boolean addUser(User user);
		
		//to find a particular user
		User getByEmail(String email);
		
		//to add address in db
		boolean addAddress(Address address);
		
		Address getBillingAddress(User user);
		List<Address> getShippingAddresses(User user);
		
		//alternative to above 2 methods
		//Address getBillingAddress(int user_id);
		//List<Address> getShippingAddresses(int user_id);
		
		//to update cart details to db when user adds item to cartS 
		boolean updateCart(Cart cart);

}
