package catolica.mindplus.mindplus.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

public class KeycloakJwtAuthenticationConverter extends JwtAuthenticationConverter {
    
    public KeycloakJwtAuthenticationConverter() {
        setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        if (roles == null) {
            roles = List.of();
        }

        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());
    }
}