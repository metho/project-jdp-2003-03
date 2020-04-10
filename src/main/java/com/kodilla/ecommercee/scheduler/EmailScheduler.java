package com.kodilla.ecommercee.scheduler;

import com.kodilla.ecommercee.config.AdminConfig;
import com.kodilla.ecommercee.entity.Mail;
import com.kodilla.ecommercee.entity.UserOrder;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class EmailScheduler {

    private static final String SUBJECT ="Email: New order list:";

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(cron ="0 0/30 * * * *")
    public void sendEmail(){
        List<UserOrder> userOrdersToSendEmail = orderRepository.findByMailSentFalse();
        for(UserOrder userOrder: userOrdersToSendEmail){
            emailService.send(new Mail(
                    adminConfig.getAdminMail(), SUBJECT, "New order for user " + userOrder.getUser().getName()));
            orderRepository.setMailSent(userOrder.getId(), true);

        }
    }
}
