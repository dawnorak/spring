package com.springboot.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("applicant")
                .password("password")
                .roles("APPLICANT")
                .build();

        UserDetails recruiter = User.withDefaultPasswordEncoder()
                .username("recruiter")
                .password("password")
                .roles("RECRUITER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("admin")
                .build();

        return new InMemoryUserDetailsManager(user, recruiter, admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/allApplicants").permitAll();
                    auth.requestMatchers("/addApplicant").hasRole("APPLICANT");
                    auth.requestMatchers("/updateApplicant").hasRole("RECRUITER");
                    auth.requestMatchers("/addWatchlist").hasRole("APPLICANT");
                    auth.requestMatchers("/allWatchlist").hasRole("ADMIN");
                    auth.requestMatchers("/allWatchlist/{watchlistId}").hasRole("APPLICANT");
                    auth.requestMatchers("/deleteWatchlist/{watchlistId}").hasRole("APPLICANT");
                    auth.requestMatchers("/addUser").hasRole("ADMIN");
                    auth.requestMatchers("/allUsers").hasRole("ADMIN");
                    auth.requestMatchers("/addNotification").hasRole("RECRUITER");
                    auth.requestMatchers("/allNotifications").hasRole("ADMIN");
                    auth.requestMatchers("/addJob").hasRole("RECRUITER");
                    auth.requestMatchers("/deleteJob/{jobId}").hasRole("RECRUITER");
                    auth.requestMatchers("/allJobs").permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
