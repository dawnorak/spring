package com.springboot.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Notification {

    @Id
    private Long notificationId;
    private String jobTitle;
    private String jobCompany;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "job_id",
            referencedColumnName = "jobId"
    )
    private Job job;
}
