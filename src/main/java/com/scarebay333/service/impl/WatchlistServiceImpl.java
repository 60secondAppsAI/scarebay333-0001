package com.scarebay333.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.scarebay333.dao.GenericDAO;
import com.scarebay333.service.GenericService;
import com.scarebay333.service.impl.GenericServiceImpl;
import com.scarebay333.dao.WatchlistDAO;
import com.scarebay333.domain.Watchlist;
import com.scarebay333.dto.WatchlistDTO;
import com.scarebay333.dto.WatchlistSearchDTO;
import com.scarebay333.dto.WatchlistPageDTO;
import com.scarebay333.dto.WatchlistConvertCriteriaDTO;
import com.scarebay333.dto.common.RequestDTO;
import com.scarebay333.dto.common.ResultDTO;
import com.scarebay333.service.WatchlistService;
import com.scarebay333.util.ControllerUtils;





@Service
public class WatchlistServiceImpl extends GenericServiceImpl<Watchlist, Integer> implements WatchlistService {

    private final static Logger logger = LoggerFactory.getLogger(WatchlistServiceImpl.class);

	@Autowired
	WatchlistDAO watchlistDao;

	


	@Override
	public GenericDAO<Watchlist, Integer> getDAO() {
		return (GenericDAO<Watchlist, Integer>) watchlistDao;
	}
	
	public List<Watchlist> findAll () {
		List<Watchlist> watchlists = watchlistDao.findAll();
		
		return watchlists;	
		
	}

	public ResultDTO addWatchlist(WatchlistDTO watchlistDTO, RequestDTO requestDTO) {

		Watchlist watchlist = new Watchlist();

		watchlist.setWatchlistId(watchlistDTO.getWatchlistId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		watchlist = watchlistDao.save(watchlist);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Watchlist> getAllWatchlists(Pageable pageable) {
		return watchlistDao.findAll(pageable);
	}

	public Page<Watchlist> getAllWatchlists(Specification<Watchlist> spec, Pageable pageable) {
		return watchlistDao.findAll(spec, pageable);
	}

	public ResponseEntity<WatchlistPageDTO> getWatchlists(WatchlistSearchDTO watchlistSearchDTO) {
	
			Integer watchlistId = watchlistSearchDTO.getWatchlistId(); 
 			String sortBy = watchlistSearchDTO.getSortBy();
			String sortOrder = watchlistSearchDTO.getSortOrder();
			String searchQuery = watchlistSearchDTO.getSearchQuery();
			Integer page = watchlistSearchDTO.getPage();
			Integer size = watchlistSearchDTO.getSize();

	        Specification<Watchlist> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, watchlistId, "watchlistId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Watchlist> watchlists = this.getAllWatchlists(spec, pageable);
		
		//System.out.println(String.valueOf(watchlists.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(watchlists.getTotalPages()));
		
		List<Watchlist> watchlistsList = watchlists.getContent();
		
		WatchlistConvertCriteriaDTO convertCriteria = new WatchlistConvertCriteriaDTO();
		List<WatchlistDTO> watchlistDTOs = this.convertWatchlistsToWatchlistDTOs(watchlistsList,convertCriteria);
		
		WatchlistPageDTO watchlistPageDTO = new WatchlistPageDTO();
		watchlistPageDTO.setWatchlists(watchlistDTOs);
		watchlistPageDTO.setTotalElements(watchlists.getTotalElements());
		return ResponseEntity.ok(watchlistPageDTO);
	}

	public List<WatchlistDTO> convertWatchlistsToWatchlistDTOs(List<Watchlist> watchlists, WatchlistConvertCriteriaDTO convertCriteria) {
		
		List<WatchlistDTO> watchlistDTOs = new ArrayList<WatchlistDTO>();
		
		for (Watchlist watchlist : watchlists) {
			watchlistDTOs.add(convertWatchlistToWatchlistDTO(watchlist,convertCriteria));
		}
		
		return watchlistDTOs;

	}
	
	public WatchlistDTO convertWatchlistToWatchlistDTO(Watchlist watchlist, WatchlistConvertCriteriaDTO convertCriteria) {
		
		WatchlistDTO watchlistDTO = new WatchlistDTO();
		
		watchlistDTO.setWatchlistId(watchlist.getWatchlistId());

	

		
		return watchlistDTO;
	}

	public ResultDTO updateWatchlist(WatchlistDTO watchlistDTO, RequestDTO requestDTO) {
		
		Watchlist watchlist = watchlistDao.getById(watchlistDTO.getWatchlistId());

		watchlist.setWatchlistId(ControllerUtils.setValue(watchlist.getWatchlistId(), watchlistDTO.getWatchlistId()));



        watchlist = watchlistDao.save(watchlist);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WatchlistDTO getWatchlistDTOById(Integer watchlistId) {
	
		Watchlist watchlist = watchlistDao.getById(watchlistId);
			
		
		WatchlistConvertCriteriaDTO convertCriteria = new WatchlistConvertCriteriaDTO();
		return(this.convertWatchlistToWatchlistDTO(watchlist,convertCriteria));
	}







}
