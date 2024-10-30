package com.scarebay333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.scarebay333.domain.Watchlist;
import com.scarebay333.dto.WatchlistDTO;
import com.scarebay333.dto.WatchlistSearchDTO;
import com.scarebay333.dto.WatchlistPageDTO;
import com.scarebay333.dto.WatchlistConvertCriteriaDTO;
import com.scarebay333.service.GenericService;
import com.scarebay333.dto.common.RequestDTO;
import com.scarebay333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WatchlistService extends GenericService<Watchlist, Integer> {

	List<Watchlist> findAll();

	ResultDTO addWatchlist(WatchlistDTO watchlistDTO, RequestDTO requestDTO);

	ResultDTO updateWatchlist(WatchlistDTO watchlistDTO, RequestDTO requestDTO);

    Page<Watchlist> getAllWatchlists(Pageable pageable);

    Page<Watchlist> getAllWatchlists(Specification<Watchlist> spec, Pageable pageable);

	ResponseEntity<WatchlistPageDTO> getWatchlists(WatchlistSearchDTO watchlistSearchDTO);
	
	List<WatchlistDTO> convertWatchlistsToWatchlistDTOs(List<Watchlist> watchlists, WatchlistConvertCriteriaDTO convertCriteria);

	WatchlistDTO getWatchlistDTOById(Integer watchlistId);







}





