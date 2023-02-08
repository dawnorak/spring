package com.springboot.jobportal.controller;

import com.springboot.jobportal.entity.Watchlist;
import com.springboot.jobportal.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/addWatchlist")
    public Watchlist addWatchlist(@RequestBody Watchlist watchlist){
        return watchlistService.saveWatchlist(watchlist);
    }

    @GetMapping("/allWatchlist")
    public List<Watchlist> allWatchlist(){
        return watchlistService.getAllWatchlist();
    }

    @DeleteMapping("/deleteWatchlist/{watchlistId}")
    public void deleteWatchlist(@PathVariable("watchlistId") Long watchlistId){
        watchlistService.deleteWatchlistById(watchlistId);
    }
}
