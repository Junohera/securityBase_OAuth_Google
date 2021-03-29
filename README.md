```properties
# application-oauth.properties

#################### google ####################
spring.security.oauth2.client.registration.google.client-id=${id}
spring.security.oauth2.client.registration.google.client-secret=${secret}
spring.security.oauth2.client.registration.google.scope=email

#################### facebook ####################
spring.security.oauth2.client.registration.facebook.client-id=${id}
spring.security.oauth2.client.registration.facebook.client-secret=${secret}
spring.security.oauth2.client.registration.facebook.scope=email

#################### naver ####################

#registration
spring.security.oauth2.client.registration.naver.client-id=${id}
spring.security.oauth2.client.registration.naver.client-secret=${secret}
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
spring.security.oauth2.client.registration.kakao.client-id=${id}
spring.security.oauth2.client.registration.kakao.client-name=kakao
spring.security.oauth2.client.registration.kakao.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.kakao.client-authentication-method=post
spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code

#provider
spring.security.oauth2.client.provider.kakao.authorization-uri=https://kauth.kakao.com/oauth/authorize
spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
spring.security.oauth2.client.provider.kakao.user-name-attribute=kakao_account
```
