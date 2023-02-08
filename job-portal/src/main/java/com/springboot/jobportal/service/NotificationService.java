package com.springboot.jobportal.service;

import com.springboot.jobportal.entity.Notification;
import com.springboot.jobportal.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<Notification> allNotification(){
        return notificationRepository.findAll();
    }

}
