package google.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/ok")
    public Map<String, Object> loginOk(HttpSession httpSession){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("profile", httpSession.getAttribute("user"));
        return response;
    }
}
