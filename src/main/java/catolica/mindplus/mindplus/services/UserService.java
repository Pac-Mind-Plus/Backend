package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;
import catolica.mindplus.mindplus.repositories.HistoricRepository;
import io.swagger.v3.core.util.Json;

@Service
public class UserService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${security.oauth2.resourceserver.jwt.client-ids}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.admin-username}")
    private String adminUsername;

    @Value("${keycloak.admin-password}")
    private String adminPassword;

    private final RestTemplate restTemplate = new RestTemplate();

    private String getAdminToken() {
        String url = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakServerUrl, keycloakRealm);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("username", adminUsername);
        formData.add("password", adminPassword);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        return response.getBody().get("access_token").toString();
    }

    public JsonNode getUserById(String userId) throws JsonProcessingException{
        String adminToken = getAdminToken();
        String url = String.format("%s/admin/realms/%s/users/%s", keycloakServerUrl, keycloakRealm, userId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + adminToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.getBody());
    }
}

    // @Autowired
    // UsersRepository repository;

    // @Autowired
    // ActionsGroupsRepository actionsGroupsRepository;

    // @Autowired
    // HistoricRepository historicRepository;

    // public Optional<User> getById(int id) {
    //     return repository.findById(id);
    // }

    // public List<Historic> getUserHitoricPaginated(int userId, int page, int pageSize) {
    //     var user = repository.findById(userId);

    //     if (user.isPresent()) {
    //         PageRequest pageable = PageRequest.of(page, pageSize);
    //         return this.historicRepository.findByOwner(user.get(), pageable);
    //     };

    //     throw new NoSuchElementException();
    // }

    // public List<ActionGroups> getUserActionGroupsPaginated(int userId, int page, int pageSize) {
    //     var user = repository.findById(userId);

    //     if (user.isPresent()) {
    //         PageRequest pageable = PageRequest.of(page, pageSize);
    //         return this.actionsGroupsRepository.findByOwner(user.get(), pageable);
    //     };

    //     throw new NoSuchElementException();
    // }

	// public void setRepository(UsersRepository repository) {
	// 	this.repository = repository;
	// }

