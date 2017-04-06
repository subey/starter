package net.subey.starter;

import net.subey.starter.example.Example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories("net.subey.starter.example.repository")
@EntityScan("net.subey.starter.example.domain")
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Example example = ctx.getBean(Example.class);
        example.run();
    }


}