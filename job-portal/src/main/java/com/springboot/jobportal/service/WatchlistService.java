package com.springboot.jobportal.service;

import com.springboot.jobportal.entity.Watchlist;
import com.springboot.jobportal.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public Watchlist saveWatchlist(Watchlist watchlist){
        return watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getAllWatchlist(){
        return watchlistRepository.findAll();
    }

    public void deleteWatchlistById(Long watchlistId){
        watchlistRepository.deleteById(watchlistId);
    }

}
