package com.example.testauthservice.config;

import com.example.testauthservice.constants.UrlConstants;
import com.example.testauthservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //@Autowired
    //public void setUserService(UserService userService) {
    //    this.userService = userService;
    //}

    @Autowired
    public SecurityConfig(UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Lazy
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(authenticationProvider());
    }

    //@Autowired
    //public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.authenticationProvider(authenticationProvider());
    //}


    //@Autowired
    //public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.authenticationProvider(authenticationProvider());

    //}

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:3000"); // or "http://localhost:3000" for a specific origin
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter();
//    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // or "http://localhost:3000" for a specific origin
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(UrlConstants.REGISTER_URL).permitAll()
                                .requestMatchers(UrlConstants.LOGIN_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage(UrlConstants.LOGIN_URL)
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            // Generate JWT token
                            String jwtToken = jwtTokenProvider.generateToken(authentication);

                            // Add the token to the response

                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                            response.setHeader("Access-Control-Max-Age", "3600");
                            response.setHeader("Access-Control-Allow-Headers", "*");
                            response.setHeader("Access-Control-Allow-Credentials", "true");
                            response.setContentType("application/json");
                            response.getWriter().write("Bearer " + jwtToken);
                        })
                        .failureHandler((request, response, exception) -> {
                            // Customize your failure handling here
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write(exception.getMessage());
                            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                            response.setHeader("Access-Control-Max-Age", "3600");
                            response.setHeader("Access-Control-Allow-Headers", "*");
                            response.setHeader("Access-Control-Allow-Credentials", "true");
                        })
                )
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails =
                User.withUsername("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
     */
}
