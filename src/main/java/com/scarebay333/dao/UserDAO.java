package com.scarebay333.dao;

import java.util.List;

import com.scarebay333.dao.GenericDAO;
import com.scarebay333.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


