package leica.blog.config.auth;

import leica.blog.entity.Member;
import leica.blog.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;


// 여기서 google, Kakao, naver 필터링 해주는 부분
@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;


    @Builder
    public OAuthAttributes(Map<String,Object> attributes,String nameAttributeKey,String name,String email){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId,String userNameAttributeName, Map<String,Object> attributes){
        switch (registrationId){
            case "google":
                return ofGoogle(userNameAttributeName,attributes);
        }
        return null;
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
