package pavlo.juniortask.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        @Qualifier("userService")
        UserDetailsService userDetailsService;

        @Bean
       public DaoAuthenticationProvider provider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            daoAuthenticationProvider.setUserDetailsService(userDetailsService);
            return daoAuthenticationProvider;
       }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
                 .authorizeRequests()
                 .antMatchers(HttpMethod.POST,"/addAvatar").permitAll()
                 .antMatchers(HttpMethod.POST,"/saveUser").permitAll()
                 .antMatchers("/", "/home").permitAll()
                 .anyRequest().authenticated()
                 .antMatchers("/admin/**").hasRole("ADMIN")
                 .and()
                 .formLogin()
                 .loginPage("/login")
                 .successForwardUrl("/successURL")//handle with post mapping in controller
                 .failureUrl("/login?error").permitAll()
                 .permitAll()
                 .and()
                 .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                 logoutSuccessUrl("/login")
                 .permitAll();
     }
}
