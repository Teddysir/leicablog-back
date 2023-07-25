package leica.blog.oauth.info;

import leica.blog.oauth.entity.ProviderType;
import leica.blog.oauth.info.impl.GoogleOAuth2UserInfo;
import leica.blog.oauth.info.impl.NaverOAuth2UserInfo;
import leica.blog.oauth.info.impl.KakaoOAuth2UserInfo;

import java.util.Map;

import static leica.blog.oauth.entity.ProviderType.*;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
