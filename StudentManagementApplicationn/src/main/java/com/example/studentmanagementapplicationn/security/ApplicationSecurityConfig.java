package com.example.studentmanagementapplicationn.security;

import com.example.studentmanagementapplicationn.jwt.AuthEntryPointJwt;
import com.example.studentmanagementapplicationn.jwt.AuthTokenFilter;
import com.example.studentmanagementapplicationn.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
/**
 * It provides AOP security on methods. It enables @PreAuthorize, @PostAuthorize,
 * it also supports JSR-250
 */

/**
 *  WebSecurityConfigurerAdapter allows Spring to find and automatically apply the class
 *  to the global Web Security.
 */

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * We override the configure(HttpSecurity http) method from WebSecurityConfigurerAdapter interface.
     * It tells Spring Security how we configure CORS and CSRF, when we want to require all users
     * to be authenticated or not, which filter (AuthTokenFilter) and when we want it
     * to work (filter before UsernamePasswordAuthenticationFilter),
     * which Exception Handler is chosen (AuthEntryPointJwt).
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /*
        httpSecurity
                //.csrf().disable()
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                //.addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/main", "/login", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/student/**").hasRole(ApplicationUserRole.STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/main", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                    .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")
                    .rememberMeParameter("remember-me")
                    .and()
                    .logout()
                .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
         */
        /*
        httpSecurity
                .csrf().disable() //for POSTMAN
                .authorizeRequests()
                .antMatchers("/", "/register/**", "/login")
                .permitAll()
                .antMatchers("/admin/*")

                .hasAuthority("ADMIN")
                .antMatchers("/teacher/*")
                .hasAnyAuthority("ADMIN", "TEACHER")
                .antMatchers("/student/*")
                .hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/main", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login");
         */
        httpSecurity.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                //.antMatchers("/student/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
