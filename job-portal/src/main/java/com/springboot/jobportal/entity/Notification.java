package com.springboot.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "applicant_notification_map",
            joinColumns = @JoinColumn(
                    name = "notification_id",
                    referencedColumnName = "notificationId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "applicant_id",
                    referencedColumnName = "applicantId"
            )
    )
    private List<Applicant> applicant;

    public void addApplicant(Applicant applicants){
        if (applicant == null){
            applicant = new ArrayList<>();
        }
        applicant.add(applicants);
    }
}
