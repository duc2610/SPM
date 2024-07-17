package com.swp391.SPM.security;

import com.swp391.SPM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class UserConfiguration {


    @Autowired
    private CustomOAuth2UserService customOidcUserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers("/spm/home").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/spm/view-pitch").permitAll()
                        .requestMatchers("/view/san-bong-detail").permitAll()
                        .requestMatchers("/register/showRegisterForm").permitAll()
                        .requestMatchers("/register/process").permitAll()
                        .requestMatchers("/forgetPassword").permitAll()
                        .requestMatchers("/sendEmail").permitAll()
                        .requestMatchers("/checkNumber").permitAll()
                        .requestMatchers("/resetPassword").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form.loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/spm/home", true)
                                .permitAll()
                                .failureUrl("/showLoginPage?error=true")
                )
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .loginPage("/showLoginPage")
                                .successHandler(oAuth2AuthenticationSuccessHandler())
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/logout") // Endpoint cho việc logout
                                .logoutSuccessUrl("/spm/home") // Đường dẫn sau khi logout thành công
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                );
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            userService.saveUserByEmail(oidcUser);
            response.sendRedirect("/spm/home");
        };
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static.assets/**", "/assets/**");
    }
}
