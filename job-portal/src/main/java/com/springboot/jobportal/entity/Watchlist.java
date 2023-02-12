package com.springboot.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Watchlist {

    @Id
    @SequenceGenerator(
            name = "watchlist_sequence",
            sequenceName = "watchlist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "watchlist_sequence"
    )
    private Long watchlistId;
    private String job_category;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "applicant_id",
            referencedColumnName = "applicantId"
    )
    private Applicant applicant;
}
