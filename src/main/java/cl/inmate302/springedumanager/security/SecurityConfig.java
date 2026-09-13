package cl.inmate302.springedumanager.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig{
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  UserDetailsService users(PasswordEncoder enc, @Value("${SpringEduManager.security.admin.username}") String au,
      @Value("${SpringEduManager.security.admin.password}") String ap, @Value("${SpringEduManager.security.user.username}") String uu,
      @Value("${SpringEduManager.security.user.password}") String up) {
    return new InMemoryUserDetailsManager(User.withUsername(au).password(enc.encode(ap)).roles("ADMIN").build(),
        User.withUsername(uu).password(enc.encode(up)).roles("USER").build());
  }

  @Bean
  SecurityFilterChain chain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
        .authorizeHttpRequests(
            a -> a.requestMatchers("/css/**", "/js/**", "/login", "/registro", "/api/v1/auth/**", "/demo/**")
                .permitAll().requestMatchers("/estudiantes/**", "/cursos/nuevo", "/cursos/eliminar/**","/evaluaciones/nueva").hasRole("ADMIN")
                .anyRequest().authenticated())
        .formLogin(f -> f.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll())
        .logout(l -> l.logoutSuccessUrl("/login?logout")).httpBasic(b -> {
        });
    return http.build();
  }
}
