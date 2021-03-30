package com.juno.club.security.handler;

import com.juno.club.security.dto.ClubAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_URL = "/login?error=true";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "아이디나 비밀번호가 맞지 않습니다.";
        }
        else if (exception instanceof DisabledException) {
            errorMessage = "계정이 비활성화되었습니다.";
        }
        else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호 유효기간이 만료되었습니다.";
        }
        else if (exception instanceof OAuth2AuthenticationException) {
            errorMessage = "간편로그인시 문제가 발생하였습니다.";
        }
        else {
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.";
        }

        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }
}
