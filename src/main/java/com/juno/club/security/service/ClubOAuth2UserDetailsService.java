package com.juno.club.security.service;

import com.juno.club.entity.ClubMemberRole;
import com.juno.club.security.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.juno.club.entity.ClubMember;
import com.juno.club.repository.ClubMemberRepository;
import com.juno.club.security.dto.ClubAuthMemberDTO;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("--------------------------------------");
        log.info("userRequest:" + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User =  super.loadUser(userRequest);

        log.info("==============================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k +":" + v);
        });

        OAuthAttributes oauthInfo = OAuthAttributes.of(oAuth2User, clientName);

        log.info("oauthInfo : " + oauthInfo);

        // 유효하지않은 email(id)일 경우,(ex:카카오 이메일 미동의시)
        if (oauthInfo.getEmail() == null || oauthInfo.getEmail().equals("")) {
            throw new OAuth2AuthenticationException(new OAuth2Error("email"));
        }

        ClubMember member = saveSocialMember(oauthInfo);

        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                true,   //fromSocial
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        clubAuthMember.setName(member.getName());


        return clubAuthMember;

    }


    private ClubMember saveSocialMember(OAuthAttributes oauthInfo){

        //기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만
        Optional<ClubMember> result = repository.findByEmail(oauthInfo.getEmail(), true);

        if(result.isPresent()){
            return result.get();
        }

        //없다면 회원 추가 패스워드는 1111 이름은 그냥 이메일 주소로
        ClubMember clubMember = ClubMember.builder().email(oauthInfo.getEmail())
                .name(oauthInfo.getName())
                .password( passwordEncoder.encode("1111") )
                .fromSocial(true)
                .socialType(oauthInfo.getSocialType())
                .build();

        clubMember.addMemberRole(ClubMemberRole.USER);


        repository.save(clubMember);

        return clubMember;
    }
}
