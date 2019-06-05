package google.config.successHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import google.GoogleUser;
import google.domain.User;
import google.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class GoogleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    private HttpSession httpSession;
    private ObjectMapper objectMapper;

    public GoogleAuthenticationSuccessHandler(HttpSession httpSession, ObjectMapper objectMapper) {
        this.httpSession = httpSession;
        this.objectMapper = objectMapper;
    }

    @Override //구글 계정 세션 저장
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        httpSession.setAttribute("user", createUser(getGoogleUser(authentication)));
        response.sendRedirect("/ok");
    }

    // OAuth 인증정보를 통해 객체 생성
    // Authentication로 구글 계정 정보 얻음 [단 map으로 되어있음]ptj
    private GoogleUser getGoogleUser(Authentication authentication){
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        return objectMapper.convertValue(oAuth2Authentication.getUserAuthentication().getDetails(), GoogleUser.class);
    }

    private User createUser(GoogleUser googleUser){
        User saveUser = userRepository.findByEmail(googleUser.getEmail());

        if(saveUser == null){
            User newUser = googleUser._toUser();
            saveUser = userRepository.save(newUser);
        }

        return saveUser;
    }
}
