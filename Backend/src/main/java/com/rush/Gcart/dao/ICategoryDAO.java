package com.rush.Gcart.dao;

import java.util.List;

import com.rush.Gcart.model.Category;



public interface ICategoryDAO {
	
	boolean add(Category c);
	boolean update(Category c);
	boolean delete(Category c);
	List<Category> list();

    Category get(int id);

}
