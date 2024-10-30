package com.scarebay333.dao;

import java.util.List;

import com.scarebay333.dao.GenericDAO;
import com.scarebay333.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


