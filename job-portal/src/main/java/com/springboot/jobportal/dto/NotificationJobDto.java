package com.springboot.jobportal.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationJobDto {
    private Long notificationId;
    private String jobTitle;
    private String jobCompany;
    private Long jobId;
}
