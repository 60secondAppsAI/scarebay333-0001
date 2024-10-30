package com.scarebay333.dao;

import java.util.List;

import com.scarebay333.dao.GenericDAO;
import com.scarebay333.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


