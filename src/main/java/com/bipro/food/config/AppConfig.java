package com.bipro.food.config;


import com.bipro.food.security.JwtAuthenticationEntryPoint;
import com.bipro.food.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    private JwtAuthenticationEntryPoint point;

//    public AppConfig(JwtAuthenticationEntryPoint point) {
//        this.point = point;
//    }
//    private JwtAuthenticationFilter filter;
//
//
//    public AppConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//                          JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.point = jwtAuthenticationEntryPoint;
//        this.filter= jwtAuthenticationFilter;
//    }

//    @Bean
//
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf(csrf->csrf.disable())
//                .authorizeRequests()
//                .requestMatchers("/api/admin/**").hasAnyRole("ADMIN","ROLE_RESTAURANT_OWNER")
//                .requestMatchers("/api/**").authenticated()
//                .anyRequest().permitAll()
//                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(csrf->csrf.disable())
//                .cors(cors->cors.configurationSource(corsConfigurationSource()));
//
//        http.addFilterBefore(new JwtTokenValidator(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

        http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER" , "ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()));

        return http.build();

    }


    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration cfg = new CorsConfiguration();

                cfg.setAllowedOrigins(Arrays.asList(
                        "https://vercel.app.mastermenu.com",
                        "http://localhost:3000"
                ));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);

                return cfg;
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
