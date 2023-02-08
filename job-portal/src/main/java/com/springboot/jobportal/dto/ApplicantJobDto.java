package com.springboot.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantJobDto {

    private Long applicantId;
    private String firstName;
    private String lastName;
    private Integer experience;
    private Long jobId;
    private String applicantStatus;
}
