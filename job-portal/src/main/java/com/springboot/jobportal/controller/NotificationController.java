package com.springboot.jobportal.controller;

import com.springboot.jobportal.entity.Notification;
import com.springboot.jobportal.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("allNotifications")
    public List<Notification> getNotification(){
        return notificationService.allNotification();
    }

    @PostMapping("/addNotification")
    public Notification postNotification(Notification notification){
        return notificationService.addNotification(notification);
    }
}
