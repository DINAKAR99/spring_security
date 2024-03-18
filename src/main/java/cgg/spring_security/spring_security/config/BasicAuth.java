package cgg.spring_security.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cgg.spring_security.spring_security.filters.RedirectAuthenticatedUserFilter;
import cgg.spring_security.spring_security.services.CustomUserDetailsService;

@Configuration
public class BasicAuth {
    @Autowired
    private CustomUserDetailsService s1;

    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csr -> csr.disable()).addFilterBefore(new RedirectAuthenticatedUserFilter(),
                UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        r -> r.requestMatchers("/signin").permitAll().requestMatchers("/users/**")
                                .hasRole("NORMAL").requestMatchers("/**").permitAll())
                .formLogin(login -> login.loginPage("/signin").loginProcessingUrl("/dologin")
                        .defaultSuccessUrl("/users/"))
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/signin?logout"));

        return httpSecurity.build();

        // httpSecurity
        // .authorizeHttpRequests(r ->
        // r.requestMatchers("/public/**").permitAll().anyRequest().authenticated())
        // .httpBasic(Customizer.withDefaults());

        // return httpSecurity.build();

        // httpSecurity
        // .authorizeHttpRequests(
        // r ->
        // r.requestMatchers("/public/**").hasRole("USER").anyRequest().authenticated())
        // .httpBasic(Customizer.withDefaults());

        // return httpSecurity.build();

        // CsrfTokenRequestAttributeHandler requesthandler = new
        // CsrfTokenRequestAttributeHandler();
        // httpSecurity
        // .csrf(csrf ->
        // csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        // .csrfTokenRequestHandler(requesthandler))
        // .authorizeHttpRequests(
        // r -> r
        // .requestMatchers("/public/**").hasRole("USER").requestMatchers("/users/**")
        // .hasRole("USER")
        // .anyRequest().authenticated())
        // .httpBasic(Customizer.withDefaults());

        // return httpSecurity.build();

    }

    @Bean
    UserDetailsService getUserDetailsService() {

        // UserDetails userDetails =
        // User.withUsername("dinakar").password(getPasswordEncoder().encode(
        // "add")).roles("USER").build();

        // InMemoryUserDetailsManager inu1 = new
        // InMemoryUserDetailsManager(userDetails);
        return s1;

    }

    @Bean
    PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    AuthenticationProvider authenticationprovider() {

        DaoAuthenticationProvider d1 = new DaoAuthenticationProvider();

        d1.setUserDetailsService(s1);
        d1.setPasswordEncoder(getPasswordEncoder());

        return d1;
    }

}
