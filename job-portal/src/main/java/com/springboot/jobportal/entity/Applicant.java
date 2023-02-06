package com.springboot.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Applicant {

    @Id
    @SequenceGenerator(
            name = "applicant_sequence",
            sequenceName = "applicant_sequence",
            initialValue = 1200,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "applicant_sequence"
    )
    private Long applicantId;
    private String firstName;
    private String lastName;
    private Integer experience;
    private String applicantStatus;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "job_id",
            referencedColumnName = "jobId"
    )
    private Job job;

}
