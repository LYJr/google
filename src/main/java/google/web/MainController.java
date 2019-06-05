package google.web;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    private HttpSession httpSession;

    public MainController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/ok")
    public Map<String, Object> ok(){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("login", httpSession.getAttribute("user"));
        return response;
    }

}
