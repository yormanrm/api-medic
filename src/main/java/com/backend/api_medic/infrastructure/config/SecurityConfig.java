package com.backend.api_medic.infrastructure.config;

import com.backend.api_medic.application.CredentialService;
import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.infrastructure.handler.CustomAccessDeniedHandler;
import com.backend.api_medic.infrastructure.handler.CustomAuthenticationEntryPoint;
import com.backend.api_medic.infrastructure.jwt.JwtAuthenticationFilter;
import com.backend.api_medic.infrastructure.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/doctor/register").hasRole("ADMIN")
                        .requestMatchers("/api/v1/employee/register").hasRole("ADMIN")
                        .requestMatchers("/api/v1/appointment/register").hasAnyRole("ADMIN","PHARMACIST")
                        .requestMatchers("/api/v1/doctor/get-all").hasAnyRole("ADMIN","PHARMACIST")
                        .requestMatchers("/api/v1/doctor/get-byID").hasAnyRole("ADMIN","PHARMACIST")
                        .requestMatchers("/api/v1/doctor/search").hasAnyRole("ADMIN","PHARMACIST")
                        .requestMatchers("/api/v1/patient/register").hasAnyRole("ADMIN","PHARMACIST")
                        .requestMatchers("/api/v1/patient/get-all").hasAnyRole("ADMIN","PHARMACIST")
                        .requestMatchers("/api/v1/appointment/details/update").hasRole("DOCTOR")
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, credentialService);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Credential credential = credentialService.findByUsername(username);
            return new User(
                    credential.getUsername(),
                    credential.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + credential.getRole()))
            );
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
