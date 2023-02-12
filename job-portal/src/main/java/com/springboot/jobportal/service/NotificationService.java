package com.springboot.jobportal.service;

import com.springboot.jobportal.dto.NotificationJobDto;
import com.springboot.jobportal.entity.Job;
import com.springboot.jobportal.entity.Notification;
import com.springboot.jobportal.repository.JobRepository;
import com.springboot.jobportal.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JobRepository jobRepository;

    public NotificationJobDto convertEntityToDto(Notification notification){
        NotificationJobDto notificationJobDto = new NotificationJobDto();
        notificationJobDto.setNotificationId(notification.getNotificationId());
        notificationJobDto.setJobId(notification.getJob().getJobId());
        notificationJobDto.setJobTitle(notification.getJobTitle());
        notificationJobDto.setJobCompany(notification.getJobCompany());

        return notificationJobDto;
    }

    public Notification addNotification(NotificationJobDto notificationJobDto){
        Job job = jobRepository.findById(notificationJobDto.getJobId()).get();

        Notification notification = Notification.builder()
                .notificationId(notificationJobDto.getNotificationId())
                .jobTitle(job.getTitle())
                .jobCompany(job.getCompany())
                .job(job)
                .build();

        return notificationRepository.save(notification);
    }

    public List<NotificationJobDto> allNotification(){
        return notificationRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
