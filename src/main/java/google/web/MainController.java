package google.web;

import google.GoogleUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class MainController {

    private HttpSession httpSession;

    public MainController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }


    @GetMapping("/ok")
    public Map<String, Object> ok(){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("P", httpSession.getAttribute("user"));
        return response;
    }

}
