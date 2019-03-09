package handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.rush.Gcart.dao.IUserDAO;
import com.rush.Gcart.model.Address;
import com.rush.Gcart.model.Cart;
import com.rush.Gcart.model.User;

import flow.model.RegisterModel;

@Component("registerHandler")
public class RegisterHandler {
	

	@Autowired
	private IUserDAO userDAO;
	
	public RegisterModel init()
	{
		System.out.println(new RegisterModel().hashCode());
		return (new RegisterModel());
	}
	
	public void addUser(RegisterModel registerModel,User user)
	{
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing)
	{
		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model)
	{
		String transitionValue = "success";
		
		//fetch user
		User user = model.getUser();
		
		if(user.getRole().equals("USER"))
		{
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//save user
		userDAO.addUser(user);
		
		//get address
		Address billing = model.getBilling();	
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}

	public String validateUser(User user, MessageContext error)
	{
		
		String transitionValue = "success";

		//check the uniqueness of email
		if(userDAO.getByEmail(user.getEmail()) != null)
		{
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email id already registered").build());
			transitionValue = "failure";
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword()))
		{
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password does not match with confirm Password ").build());
			transitionValue = "failure";
		}
		return transitionValue;
		
	}

}
