package com.springboot.jobportal.controller;

import com.springboot.jobportal.dto.WatchlistApplicantDto;
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
    public Watchlist addWatch(@RequestBody WatchlistApplicantDto watchlistApplicantDto){
        return watchlistService.saveWatchlist(watchlistApplicantDto);
    }

    @GetMapping("/allWatchlist")
    public List<WatchlistApplicantDto> allWatchlist(){
        return watchlistService.getWatchlist();
    }

    @DeleteMapping("/deleteWatchlist/{watchlistId}")
    public void deleteWatchlist(@PathVariable("watchlistId") Long watchlistId){
        watchlistService.deleteWatchlistById(watchlistId);
    }

    @GetMapping("/allWatchlist/{applicantId}")
    public List<Watchlist> allWatchlistById(@PathVariable("applicantId") Long applicantId){
        return watchlistService.getWatchlistById(applicantId);
    }
}
