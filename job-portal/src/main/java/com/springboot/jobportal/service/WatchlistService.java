package com.springboot.jobportal.service;

import com.springboot.jobportal.dto.WatchlistApplicantDto;
import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.entity.Watchlist;
import com.springboot.jobportal.repository.ApplicantRepository;
import com.springboot.jobportal.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    private WatchlistApplicantDto convertEntityToDto(Watchlist watchlist){
        WatchlistApplicantDto watchlistApplicantDto = new WatchlistApplicantDto();

        watchlistApplicantDto.setWatchlistId(watchlist.getWatchlistId());
        watchlistApplicantDto.setApplicantId(watchlist.getApplicant().getApplicantId());
        watchlistApplicantDto.setJobId(watchlist.getApplicant().getJob().getJobId());
        watchlistApplicantDto.setJobTitle(watchlist.getApplicant().getJob().getTitle());
        watchlistApplicantDto.setJobCompany(watchlist.getApplicant().getJob().getCompany());

        return watchlistApplicantDto;
    }

    public Watchlist saveWatchlist(WatchlistApplicantDto watchlistApplicantDto){
        Applicant applicant = applicantRepository.findById(watchlistApplicantDto.getApplicantId()).get();

        Watchlist watchlist = Watchlist.builder()
                .watchlistId(watchlistApplicantDto.getWatchlistId())
                .job_category(applicant.getJob().getTitle())
                .applicant(applicant)
                .build();

        return watchlistRepository.save(watchlist);
    }

    public List<WatchlistApplicantDto> getWatchlist(){
        return watchlistRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public void deleteWatchlistById(Long watchlistId){
        Applicant applicant = new Applicant();
        applicant.setApplicantId(null);

        Watchlist watchlist = new Watchlist();
        watchlist.setWatchlistId(watchlistId);
        watchlist.setApplicant(applicant);

        watchlistRepository.save(watchlist);

        watchlistRepository.deleteById(watchlistId);
    }

    public List<Watchlist> getWatchlistById(Long applicantId){
        return watchlistRepository.findAllByApplicantApplicantId(applicantId);
    }

}
