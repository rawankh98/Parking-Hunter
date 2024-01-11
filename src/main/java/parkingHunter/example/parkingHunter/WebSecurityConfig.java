package parkingHunter.example.parkingHunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserDetailsServiceImpl userDetailsService;

        @Bean
        public BCryptPasswordEncoder passwordEncoder(){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            return bCryptPasswordEncoder;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().disable().csrf().disable().authorizeRequests().antMatchers( "/login", "/signup","/","/aboutus","/userShowParking","/userShowParking/{id}","/test").permitAll().antMatchers("/adminpanel").hasAuthority("ROLE_ADMIN").antMatchers("/owner","/addparking", "/css/**").hasAuthority("ROLE_OWNER").anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/perform_login").defaultSuccessUrl("/", true).failureUrl("/signup").and().logout().logoutUrl("/perform_logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID");
        }

}
