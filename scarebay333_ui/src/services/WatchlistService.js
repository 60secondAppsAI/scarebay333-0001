import http from "../http-common";

class WatchlistService {
  getAllWatchlists(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/watchlist/watchlists`, searchDTO);
  }

  get(watchlistId) {
    return this.getRequest(`/watchlist/${watchlistId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/watchlist?field=${matchData}`, null);
  }

  addWatchlist(data) {
    return http.post("/watchlist/addWatchlist", data);
  }

  update(data) {
  	return http.post("/watchlist/updateWatchlist", data);
  }
  
  uploadImage(data,watchlistId) {
  	return http.postForm("/watchlist/uploadImage/"+watchlistId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new WatchlistService();
