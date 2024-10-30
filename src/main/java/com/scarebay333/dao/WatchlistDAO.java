package com.scarebay333.dao;

import java.util.List;

import com.scarebay333.dao.GenericDAO;
import com.scarebay333.domain.Watchlist;





public interface WatchlistDAO extends GenericDAO<Watchlist, Integer> {
  
	List<Watchlist> findAll();
	






}


