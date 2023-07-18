//package leica.blog.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class LoginService {
//    private final Environment env;
//    private final WebClient webClient;
//
//    @Value("${oauth2.registration-id.client-id}")
//    private String clientId;
//
//    @Value("${oauth2.registration-id.client-secret}")
//    private String clientSecret;
//
//    @Value("${oauth2.registration-id.redirect-uri}")
//    private String redirectUri;
//
//    @Value("${oauth2.registration-id.token-uri}")
//    private String tokenUri;
//
//    @Value("${oauth2.registration-id.resource-uri}")
//    private String resourceUri;
//
//    public void googleSocialLogin(String code, String registrationId){
//        String accessToken = getAccessToken(code, registrationId);
//        JsonNode userResourceNode = getUserResource(accessToken, registrationId);
//        System.out.println("userResource = " + userResourceNode);
//
//        String id = userResourceNode.get("id").asText();
//        String email = userResourceNode.get("email").asText();
//        String nickname = userResourceNode.get("name").asText();
//        System.out.println("id = " + id);
//        System.out.println("email = " + email);
//        System.out.println("nickname = " + nickname);
//    }
//
//    private String getAccessToken(String authorizationCode, String registrationId) {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("code", authorizationCode);
//        params.add("client_id", clientId);
//        params.add("client_secret", clientSecret);
//        params.add("redirect_uri", redirectUri);
//        params.add("grant_type", "authorization_code");
//
//        return webClient.post()
//                .uri(tokenUri.replace("{registration-id}", registrationId))
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .body(BodyInserters.fromFormData(params))
//                .retrieve()
//                .bodyToMono(JsonNode.class)
//                .block()
//                .get("access_token")
//                .asText();
//    }
//
//    private JsonNode getUserResource(String accessToken, String registrationId) {
//        return webClient.get()
//                .uri(resourceUri.replace("{registration-id}", registrationId))
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//                .retrieve()
//                .bodyToMono(JsonNode.class)
//                .block();
//    }
//}
