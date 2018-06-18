package rsi.pie.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import rsi.pie.project.interseptor.AuthorizationInterceptor;

@SpringBootApplication
public class TicketBookingApplication extends WebMvcConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor());
    }
}
