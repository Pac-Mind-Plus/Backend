package catolica.mindplus.mindplus.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>
{
    @NotEmpty
    private final List<String> clientIds;

    public KeycloakJwtAuthenticationConverter(List<String> clientIds) {
        this.clientIds = clientIds;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source)
    {
        return new JwtAuthenticationToken(source, Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source)
                .stream(), extractResourceRoles(source).stream())
                .collect(toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt)
    {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        var resourceRoles = new ArrayList<>();

        clientIds.stream().forEach(id ->
        {
            if (resourceAccess.containsKey(id))
            {
                var resource = (Map<String, List<String>>) resourceAccess.get(id);
                resource.get("roles").forEach(role -> resourceRoles.add(id + "_" + role));
            }
        });
        return resourceRoles.isEmpty() ? emptySet() : resourceRoles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(toSet());
    }
}

// public class KeycloakJwtAuthenticationConverter extends JwtAuthenticationConverter
// {
//     private final List<String> clientIds;

//     public KeycloakJwtAuthenticationConverter(List<String> clientIds) {
//         this.clientIds = clientIds;
//         setJwtGrantedAuthoritiesConverter(new KeycloakJwtGrantedAuthoritiesConverter());
//     }


    
//     private class KeycloakJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
//         @Override
//         public Collection<GrantedAuthority> convert(Jwt jwt) {
//             Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
//             if (resourceAccess == null) {
//                 return List.of();
//             }

//             return clientIds.stream()
//                     .flatMap(id -> {
//                         Map<String, List<String>> resource = (Map<String, List<String>>) resourceAccess.get(id);
//                         if (resource != null && resource.containsKey("roles")) {
//                             return resource.get("roles").stream()
//                                     .map(role -> new SimpleGrantedAuthority("ROLE_" + id + "_" + role));
//                         } else {
//                             return List.<GrantedAuthority>of().stream();
//                         }
//                     })
//                     .collect(Collectors.toList());
//         }
//     }
// }

// public class KeycloakJwtAuthenticationConverter extends JwtAuthenticationConverter {
//     public KeycloakJwtAuthenticationConverter() {
//         setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
//     }

//     private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
//         List<String> roles = jwt.getClaimAsStringList("roles");
//         if (roles == null) {
//             roles = List.of();
//         }

//         return roles.stream()
//             .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//             .collect(Collectors.toList());
//     }
// }