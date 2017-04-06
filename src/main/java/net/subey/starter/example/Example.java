package net.subey.starter.example;

import net.subey.starter.example.domain.User;
import net.subey.starter.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Example {
    private final Logger log = LoggerFactory.getLogger(Example.class);

    @Autowired
    private UserService userService;

    public void run(){
        List<User> items = userService.findByDates(
                LocalDateTime.now().minusHours(1L), LocalDateTime.now());
        log.info(items.toString());
    }
}
