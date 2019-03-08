package com.rush.Gcart.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rush.Gcart.dao.IProductDAO;
import com.rush.Gcart.model.Product;
@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl implements IProductDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public Product get(int pid) {
		try
		{			
			return sf.getCurrentSession().get(Product.class,Integer.valueOf(pid));			
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}
		return null;
		
	}

	@Override
	public List<Product> list() {
		return sf.getCurrentSession().createQuery("FROM Product" , Product.class).getResultList();
		
	}

	@Override
	public boolean add(Product p) {
		
		try 
		{			
			sf.getCurrentSession().persist(p);
			return true;
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}		
		return false;
		
	}

	@Override
	public boolean update(Product p) {
		try
		{			
			sf.getCurrentSession().update(p);
			return true;
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}		
		return false;		
		
	}

	@Override
	public boolean delete(Product p) {
		try
		{
			
			p.setActive(false);
			// call the update method
			return this.update(p);
		}
		catch(Exception ex)
		{		
			ex.printStackTrace();			
		}		
		return false;			
		
	}
	
	
	//business methods
	
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sf.getCurrentSession().createQuery(selectActiveProducts, Product.class).setParameter("active", true).getResultList();
		
	}

	@Override
	public List<Product> listActiveProductsByCategory(int cid) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryID = :categoryID";
		return sf.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class).setParameter("active", true).setParameter("categoryID",cid).getResultList();
		
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		
		return sf.getCurrentSession().createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class).setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
		
	}

}
