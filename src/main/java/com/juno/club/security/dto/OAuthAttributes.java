package com.juno.club.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Builder
@Getter
@Log4j2
@ToString
public class OAuthAttributes {

    private String nickname;
    private String name;
    private String email;
    private String picture;
    private String socialType;

    public static OAuthAttributes of(OAuth2User oAuth2User, String clientName) {
        if ("Naver".equals(clientName)) return ofNaver(oAuth2User);
        else if ("Kakao".equals(clientName)) return ofKakao(oAuth2User);
        else if ("Facebook".equals(clientName)) return ofFacebook(oAuth2User);
        else if ("GitHub".equals(clientName)) return ofGithub(oAuth2User);
        else return ofGoogle(oAuth2User);
    }

    private static OAuthAttributes ofGithub(OAuth2User oAuth2User) {
        return OAuthAttributes.builder()
                .nickname((String) oAuth2User.getAttribute("login"))
                .name((String) oAuth2User.getAttribute("name"))
                .email((String) oAuth2User.getAttribute("email"))
                .picture((String) oAuth2User.getAttribute("avatar_url"))
                .socialType("GitHub")
                .build();
    }

    private static OAuthAttributes ofGoogle(OAuth2User oAuth2User) {
        return OAuthAttributes.builder()
                .email((String) oAuth2User.getAttribute("email"))
                .picture((String) oAuth2User.getAttribute("picture"))
                .socialType("Google")
                .build();
    }

    private static OAuthAttributes ofFacebook(OAuth2User oAuth2User) {
        return OAuthAttributes.builder()
                .name((String) oAuth2User.getAttribute("name"))
                .email((String) oAuth2User.getAttribute("email"))
                .picture((String) oAuth2User.getAttribute("picture"))
                .socialType("Facebook")
                .build();
    }

    private static OAuthAttributes ofNaver(OAuth2User oAuth2User) {
        Map<String, Object> response = oAuth2User.getAttribute("response");
        return OAuthAttributes.builder()
                .nickname((String) response.get("nickname"))
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .socialType("Naver")
                .build();
    }

    private static OAuthAttributes ofKakao(OAuth2User oAuth2User) {
        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        Map<String, Object> kakao_account = oAuth2User.getAttribute("kakao_account");
        return OAuthAttributes.builder()
                .name((String) properties.get("nickname"))
                .email((String) kakao_account.get("email"))
                .picture((String) properties.get("profileImage"))
                .socialType("Kakao")
                .build();
    }

}
