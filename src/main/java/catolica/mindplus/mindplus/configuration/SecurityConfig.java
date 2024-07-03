package catolica.mindplus.mindplus.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    private final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
    {
        http.authorizeHttpRequests(authorize ->
                authorize                        
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/actuator/health/**").permitAll()
                .anyRequest().authenticated())
        .oauth2ResourceServer(server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter()).jwkSetUri(jwkSetUri)))
        .sessionManagement((sessionManagement) -> sessionManagement
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf -> csrf.disable())
        .cors(withDefaults())
        .httpBasic(withDefaults());

        return http.build();
    }
}

