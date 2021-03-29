```properties
# application-oauth.properties

# google
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_API_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_API_SECRET_KEY}
spring.security.oauth2.client.registration.google.scope=email

# facebook
spring.security.oauth2.client.registration.facebook.client-id=${FACEBOOK_API_ID}
spring.security.oauth2.client.registration.facebook.client-secret=${FACEBOOK_API_ID}
spring.security.oauth2.client.registration.facebook.scope=email

# naver
#registration
spring.security.oauth2.client.registration.naver.client-id=${NAVER_API_ID}
spring.security.oauth2.client.registration.naver.client-secret=${NAVER_API_ID}
spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.naver.authorization_grant_type=authorization_code
spring.security.oauth2.client.registration.naver.scope=name,email,profile_image
spring.security.oauth2.client.registration.naver.client-name=Naver

#provider
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token_uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user_name_attribute=response
```
