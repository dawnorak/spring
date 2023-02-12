package com.springboot.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatchlistApplicantDto {
    private Long watchlistId;
    private Long applicantId;
    private Long jobId;
    private String jobCompany;
    private String jobTitle;
}
