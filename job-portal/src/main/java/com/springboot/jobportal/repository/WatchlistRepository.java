package com.springboot.jobportal.repository;

import com.springboot.jobportal.dto.WatchlistApplicantDto;
import com.springboot.jobportal.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    List<Watchlist> findAllByApplicantApplicantId(Long applicantId);
}
