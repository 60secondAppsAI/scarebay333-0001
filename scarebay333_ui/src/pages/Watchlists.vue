<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <watchlist-table
            v-if="watchlists && watchlists.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:watchlists="watchlists"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-watchlists="getAllWatchlists"
             >

            </watchlist-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import WatchlistTable from "@/components/WatchlistTable";
import WatchlistService from "../services/WatchlistService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WatchlistTable,
  },
  data() {
    return {
      watchlists: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllWatchlists(sortBy='watchlistId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WatchlistService.getAllWatchlists(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.watchlists.length) {
					this.watchlists = response.data.watchlists;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching watchlists:", error);
        }
        
      } catch (error) {
        console.error("Error fetching watchlist details:", error);
      }
    },
  },
  mounted() {
    this.getAllWatchlists();
  },
  created() {
    this.$root.$on('searchQueryForWatchlistsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWatchlists();
    })
  }
};
</script>
<style></style>
