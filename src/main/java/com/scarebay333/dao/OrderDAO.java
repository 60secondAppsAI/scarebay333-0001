package com.scarebay333.dao;

import java.util.List;

import com.scarebay333.dao.GenericDAO;
import com.scarebay333.domain.Order;





public interface OrderDAO extends GenericDAO<Order, Integer> {
  
	List<Order> findAll();
	






}


