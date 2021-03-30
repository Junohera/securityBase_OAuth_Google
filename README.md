```properties
# application-oauth.properties

#################### google ####################
spring.security.oauth2.client.registration.google.client-id=[API_KEY_ID]
spring.security.oauth2.client.registration.google.client-secret=[API_SECRET]
spring.security.oauth2.client.registration.google.scope=email

#################### facebook ####################
spring.security.oauth2.client.registration.facebook.client-id=[API_KEY_ID]
spring.security.oauth2.client.registration.facebook.client-secret=[API_SECRET]
spring.security.oauth2.client.registration.facebook.scope=email

#################### naver ####################

#registration
spring.security.oauth2.client.registration.naver.client-id=[API_KEY_ID]
spring.security.oauth2.client.registration.naver.client-secret=[API_SECRET]
spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.naver.authorization_grant_type=authorization_code
spring.security.oauth2.client.registration.naver.scope=name,email,profile_image
spring.security.oauth2.client.registration.naver.client-name=Naver

#provider
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token_uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user_name_attribute=response

#################### kakao ####################

# registration
spring.security.oauth2.client.registration.kakao.client-id=[API_KEY_ID]
spring.security.oauth2.client.registration.kakao.client-name=Kakao
spring.security.oauth2.client.registration.kakao.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.kakao.client-authentication-method=post
spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.kakao.prompt=login
spring.security.oauth2.client.registration.kakao.scope=profile,account_email

#provider
spring.security.oauth2.client.provider.kakao.authorization-uri=https://kauth.kakao.com/oauth/authorize
spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
spring.security.oauth2.client.provider.kakao.user-name-attribute=id
```

> oauth2에 규약에 google과 facebook만 제대로 지원되고있어 springboot에서도 해당 플랫폼만 원활히 제공되어 나머지플랫폼은 귀찮은 작업을 해줘야함.

### if naver
1. response객체 까야 회원정보 나옴.

### if kakao
1. 카카오의 경우 api키로 사용자 token을 발급받고 해야함.
1. 가장 좋은건 1의 로직을 서버상에 추가하고 사용하는 것이지만, 굳이 그럴필요없이, 패킷통신툴로 토큰조회 한다음, 해당 토큰을 서버에 세팅하여 사용
> [blog ref](https://codecrafting.tistory.com/16)
